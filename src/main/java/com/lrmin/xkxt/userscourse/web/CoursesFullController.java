package com.lrmin.xkxt.userscourse.web;

import com.lrmin.utils.StringUtils;
import com.lrmin.xkxt.account.entity.Account;
import com.lrmin.xkxt.course.entity.Course;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.lrmin.framework.contoller.AbstractController;
import com.lrmin.xkxt.userscourse.service.CoursesFullService;
import com.lrmin.xkxt.userscourse.entity.CoursesFull;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/coursesFull") 
public class CoursesFullController {
    @Autowired
    private CoursesFullService  service;

    @GetMapping
    @ResponseBody
    public List<CoursesFull> gets(HttpSession session) {
        Account account = (Account) session.getAttribute("account");
        if(account == null) {
            return new ArrayList<CoursesFull>();
        }
        String accountId = account.getId();
        return service.findUsers(accountId);
    }
}