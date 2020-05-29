package com.lrmin.xkxt.course.service;

import com.lrmin.framework.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.lrmin.xkxt.course.mapper.CourseMapper;
import com.lrmin.xkxt.course.entity.Course;

@Service 
public class CourseService extends AbstractService<Course,CourseMapper> {
	@Override
	public boolean exit(Course entity) {
		return false;
	}
}