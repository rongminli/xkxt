package com.lrmin.xkxt.userscourse.web;

import com.lrmin.utils.Message;
import com.lrmin.utils.StringUtils;
import com.lrmin.xkxt.account.entity.Account;
import com.lrmin.xkxt.course.entity.Course;
import com.lrmin.xkxt.userscourse.entity.UsersCourses;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.lrmin.xkxt.userscourse.service.UsersCoursesService;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping("/usersCourses") 
public class UsersCoursesController {

    @Autowired
    private UsersCoursesService service;

    @GetMapping
    public String page(String accountId, Model model, HttpSession session){
        List<Course> courses = new ArrayList<Course>();

        if (StringUtils.isNotEmpty(accountId)){
            courses = service.getCoursesByAccountId(accountId);
        } else {
            Account account = (Account)session.getAttribute("account");
            courses = service.getCoursesByAccountId(account.getId());
        }
        model.addAttribute("courses",courses);

        return "courses-user";
    }



    @GetMapping("/select")
    public String selectCourses(String courseId, HttpSession session, Model model){
        Account account = (Account)session.getAttribute("account");

        if (StringUtils.isNotEmpty(courseId)){
            Set<Course> courses = service.conflictCheck(courseId, account.getId());
            if (courses.size() == 0) {
                UsersCourses usersCourses = new UsersCourses();
                usersCourses.init();
                usersCourses.setAccountId(account.getId());
                usersCourses.setCourseId(courseId);
                service.insert(usersCourses);
                model.addAttribute("message",
                        Message.build("success","","完成")
                );
            }else {
                StringBuilder courseNames = new StringBuilder();
                courses.stream().forEach(course -> courseNames.append(course.getName()).append(","));
                courseNames.deleteCharAt(courseNames.lastIndexOf(","));
                courseNames.insert(0,"和  ");
                courseNames.append("  的上课时间冲突");

                model.addAttribute("message",
                        Message.build("error",courseNames.toString(),"时间冲突")
                );
            }
        }
        List<Course> courses = service.getCoursesByAccountIdRev(account.getId());

        model.addAttribute("courses",courses);
        return "courses-select";
    }

    @GetMapping("/remove")
    public String removeCourses(String courseId, HttpSession session, Model model){
        UsersCourses condi = new UsersCourses();
        condi.setCourseId(courseId);
        service.deleteBy(condi);

        Account account = (Account)session.getAttribute("account");
        List<Course> courses = service.getCoursesByAccountId(account.getId());

        model.addAttribute("courses",courses);

        return "courses-user";
    }
}