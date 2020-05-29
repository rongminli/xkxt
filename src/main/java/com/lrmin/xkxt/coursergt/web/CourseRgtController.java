package com.lrmin.xkxt.coursergt.web;

import com.lrmin.utils.StringUtils;
import com.lrmin.xkxt.coursergt.entity.CourseRgt;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.beans.factory.annotation.Autowired;
import com.lrmin.framework.contoller.AbstractController;
import com.lrmin.xkxt.coursergt.service.CourseRgtService;

import java.util.Date;

@Controller
@RequestMapping("/courseRgt") 
public class CourseRgtController {

    @Autowired
    CourseRgtService service;

    @PostMapping("/edit")
    public String edit(CourseRgt courseRgt){
        if (StringUtils.isNotEmpty(courseRgt.getId())) {
            service.updateSelective(courseRgt);
        } else {
            courseRgt.init();
            courseRgt.setLastUpdateAt(new Date());
            service.insert(courseRgt);
        }
        return "redirect:/course/edit?id=" + courseRgt.getCourseId() ;
    }

    @GetMapping("/del")
    public String del(String courseId, String courseRgtId){
        service.deleteById(courseRgtId);
        return "redirect:/course/edit?id=" + courseId;
    }
}