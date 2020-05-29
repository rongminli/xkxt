package com.lrmin.xkxt.account.web;

import com.lrmin.utils.WXApi;
import com.lrmin.xkxt.accontWx.entity.AccountWx;
import com.lrmin.xkxt.accontWx.service.AccountWxService;
import com.lrmin.xkxt.account.entity.Account;
import com.lrmin.utils.PasswordEnctypt;
import com.lrmin.utils.StringUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lrmin.xkxt.account.service.AccountService;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
	@Autowired
	private AccountService service;
	@Autowired
	private AccountWxService accountWxService;

	@GetMapping("")
	public String page(Model model){
		List<Account> accounts = service.findAll();
		model.addAttribute("entities", accounts);
		return "accounts";
	}

	@GetMapping("/logout")
	public String logout(HttpSession session){

		session.removeAttribute("account");
		session.removeAttribute("userEduInfo");
		session.removeAttribute("userInfo");

		return "redirect:login";
	}

	@GetMapping("/login")
	public String login(){
		return "login";
	}


	@PostMapping("/login")
	public String auth(Account account, HttpServletRequest request){

		Account condi = new Account();
		condi.setName(account.getName());
		List<Account> re = service.findBy(condi);

		// 用户名错误
		if(re.size() == 0 ) {
			return "login";
		}

		Account conded = re.get(0);
		String encrypt = PasswordEnctypt.encrypt(account.getPassword());
		// 密码错误
		if(!encrypt.equals(conded.getPassword().trim())){
			return "login";
		}

		request.getSession().setAttribute("account",conded);

		return "redirect:../";
	}

	@PostMapping("/register")
	public String register(Account account){
		if(StringUtils.isEmpty(account.getName()) || StringUtils.isEmpty(account.getPassword())){
			return "register.html";
		}

		account.init();
		String encrypt = PasswordEnctypt.encrypt(account.getPassword());
		account.setPassword(encrypt);

		service.insert(account);

		return "login";
	}
	@GetMapping("/del")
	public String del(String id , Model model){
		if (StringUtils.isNotEmpty(id)){
			service.deleteById(id);
		}
		List<Account> all = service.findAll();
		model.addAttribute("entities", all);

		return "accounts";
	}


	@GetMapping("/login-wx")
	@ResponseBody
	public String loginWx(String code, HttpSession session){
		String sessionId = session.getId();
		String openId = WXApi.getOpenIdByCode(code);

		AccountWx condi = new AccountWx();
		condi.setOpenId(openId);
		List<AccountWx> list = accountWxService.findBy(condi);
		if (list.size() == 0) {
			return "0";
		}else{
			String accountId = list.get(0).getAccountId();
			Account account = service.findById(accountId);
			session.setAttribute("account",account);
			return sessionId;

		}
	}

	@PostMapping(value = "/login-wx")
	@ResponseBody
	public String loginWx(String password, String name, String code, HttpSession session){

		Account account = new Account();
		account.setName(name);
		account.setPassword(password);

		Account condi = new Account();
		condi.setName(account.getName());
		List<Account> re = service.findBy(condi);

		// 用户名错误
		if(re.size() == 0 ) {
			return "0";
		}

		Account conded = re.get(0);
		String encrypt = PasswordEnctypt.encrypt(account.getPassword());
		// 密码错误
		if(!encrypt.equals(conded.getPassword().trim())){
			return "0";
		}

		String openId = WXApi.getOpenIdByCode(code);
		AccountWx accountWx = new AccountWx();
		accountWx.init();
		accountWx.setOpenId(openId);
		accountWx.setAccountId(conded.getId());
		accountWxService.insert(accountWx);

		session.setAttribute("account",conded);

		return session.getId();
	}

	private class respone {
		private String sessionId;
	}
}