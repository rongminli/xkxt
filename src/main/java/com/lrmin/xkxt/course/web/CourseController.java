package com.lrmin.xkxt.course.web;

import com.lrmin.utils.StringUtils;
import com.lrmin.xkxt.account.entity.Account;
import com.lrmin.xkxt.course.entity.Course;
import com.lrmin.xkxt.coursergt.entity.CourseRgt;
import com.lrmin.xkxt.coursergt.service.CourseRgtService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import com.lrmin.xkxt.course.service.CourseService;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/course") 
public class CourseController {
	@Autowired
	private CourseService service;
	@Autowired
	private CourseRgtService courseRgtService;

	@GetMapping
	// 获取首页
	public String page(String search, Model model){
		List<Course> courses = new ArrayList<Course>();
		if (StringUtils.isNotEmpty(search)){
			courses = service.search("%" + search + "%");
		}else{
			courses = service.findAll();

		}
		model.addAttribute("courses",courses);
		model.addAttribute("search",search);
		return "courses";
	}
	@GetMapping("/view")
	// 获取详情页面
	public String view(String id, Model model){
		Course course = new Course();
		List<CourseRgt> courseRgts = new ArrayList<CourseRgt>();

		if (StringUtils.isNotEmpty(id)){
			course = service.findById(id);

			if (course != null && StringUtils.isNotEmpty(course.getId())){
				CourseRgt courseRgt = new CourseRgt();
				courseRgt.setCourseId(course.getId());
				courseRgts = courseRgtService.findBy(courseRgt);
			}
		}
		model.addAttribute("course",course);
		model.addAttribute("courseRgts",courseRgts);

		return "course-view";
	}

	@GetMapping("/edit")
	// 响应GET请求 获取编辑页面
	public String editPage(String id, Model model){
		Course course = new Course();
		List<CourseRgt> courseRgts = new ArrayList<CourseRgt>();

		if (StringUtils.isNotEmpty(id)){
			course = service.findById(id);

			if (course != null && StringUtils.isNotEmpty(course.getId())){
				CourseRgt courseRgt = new CourseRgt();
				courseRgt.setCourseId(course.getId());
				courseRgts = courseRgtService.findBy(courseRgt);
			}
		}
		model.addAttribute("course",course);
		model.addAttribute("courseRgts",courseRgts);

		return "course-edit";
	}

	@PostMapping("/edit")
	// 响应POST请求 编辑实体回到浏览页面
	public String edit(Course course, Model model){

		if (StringUtils.isNotEmpty(course.getId())){
			service.updateSelective(course);
		}else {
			course.init();
			service.insert(course);
		}
		return "redirect:" ;
	}

	@GetMapping("/del")
	// 根据id删除
	public String del(String id) {
		if (StringUtils.isNotEmpty(id)){
			service.deleteById(id);
		}

		return "redirect:" ;
	}
}