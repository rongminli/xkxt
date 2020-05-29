package com.lrmin.xkxt.profile;

import com.lrmin.utils.PasswordEnctypt;
import com.lrmin.utils.StringUtils;
import com.lrmin.xkxt.account.entity.Account;
import com.lrmin.xkxt.account.service.AccountService;
import com.lrmin.xkxt.userinfo.entity.UserInfo;
import com.lrmin.xkxt.userinfo.service.UserInfoService;
import com.lrmin.xkxt.userseduinfo.entity.UserEduInfo;
import com.lrmin.xkxt.userseduinfo.service.UserEduInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lirongmin
 * @date 2020/5/22 0022
 */

@Controller
@RequestMapping("profile")
public class ProfileController {
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private UserEduInfoService userEduInfoService;
    @Autowired
    private AccountService accountService;

    @GetMapping()
    public String profile(String accountId, HttpSession session){

        if (StringUtils.isEmpty(accountId)) {
            Account account = (Account) session.getAttribute("account");
            accountId = account.getId();
        }
        UserInfo userInfo = new UserInfo();
        UserEduInfo userEduInfo = new UserEduInfo();

        userInfo.setAccountId(accountId);
        List<UserInfo> list = userInfoService.findBy(userInfo);
        if(list.size() != 0 && list.size() == 1){
            userInfo = list.get(0);

            userEduInfo.setUserId(userInfo.getId());
            List<UserEduInfo> list1 = userEduInfoService.findBy(userEduInfo);
            if (list1.size() == 1 ) {
                userEduInfo = list1.get(0);
            }
        }

        session.setAttribute("userInfo",userInfo);
        session.setAttribute("userEduInfo",userEduInfo);

        return "profile";
    }

    @GetMapping("profile-wx")
    @ResponseBody
    public ResponseEntity<Object> profileWx(String accountId, HttpSession session){

        if (StringUtils.isEmpty(accountId)) {
            Account account = (Account) session.getAttribute("account");
            accountId = account.getId();
        }
        UserInfo userInfo = new UserInfo();
        UserEduInfo userEduInfo = new UserEduInfo();

        userInfo.setAccountId(accountId);
        List<UserInfo> list = userInfoService.findBy(userInfo);
        if(list.size() != 0 && list.size() == 1){
            userInfo = list.get(0);

            userEduInfo.setUserId(userInfo.getId());
            List<UserEduInfo> list1 = userEduInfoService.findBy(userEduInfo);
            if (list1.size() == 1 ) {
                userEduInfo = list1.get(0);
            }
        }

        Map<String,Object> result = new HashMap<String,Object>();
        result.put("userInfo",userInfo);
        result.put("userEduInfo",userEduInfo);
        ResponseEntity<Object> responseEntity = new ResponseEntity<Object>(result,HttpStatus.OK);


        return responseEntity;
    }

    @PostMapping("/update/userInfo")
    public String updateUserInfo(UserInfo userinfo, HttpSession session){
        if (StringUtils.isEmpty(userinfo.getId())){
            Account accout = (Account)session.getAttribute("account");

            userinfo.init();
            userinfo.setAccountId(accout.getId());

            userInfoService.insert(userinfo);

        }else {
            userInfoService.updateSelective(userinfo);
        }
        userinfo = userInfoService.findById(userinfo.getId());
        session.setAttribute("userInfo",userinfo);

        return "profile";
    }
    @PostMapping("/update/eduinfo")
    public String updateEduInfo(UserEduInfo userEduInfo, HttpSession session){
        if (StringUtils.isEmpty(userEduInfo.getId())){
            UserInfo userinfo = (UserInfo)session.getAttribute("userInfo");

            userEduInfo.init();
            userEduInfo.setUserId(userinfo.getId());

            userEduInfoService.insert(userEduInfo);

        }else {
            userEduInfoService.updateSelective(userEduInfo);
        }
        userEduInfo = userEduInfoService.findById(userEduInfo.getId());
        session.setAttribute("userEduInfo",userEduInfo);

        return "profile";
    }

    @PostMapping("/update/chgPassword")
    public String chgPassword(String oldPassword, String newPassword,HttpSession session){
        String enctypt = PasswordEnctypt.encrypt(oldPassword);

        Account account = (Account)session.getAttribute("account");
        // 校验密码是否相等
        if(enctypt.equals(account.getPassword())){
            String newEnctyptPassword = PasswordEnctypt.encrypt(newPassword);

            // 更新数据库
            Account newAccountPassword = new Account();
            newAccountPassword.setId(account.getId());
            newAccountPassword.setPassword(newEnctyptPassword);
            accountService.updateSelective(newAccountPassword);

            session.removeAttribute("account");

            return "redirect: ../../../../account/login";

        }else {

        }

        return "profile";
    }
}
