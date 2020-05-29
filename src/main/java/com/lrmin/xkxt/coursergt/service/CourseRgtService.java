package com.lrmin.xkxt.coursergt.service;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.lrmin.framework.service.AbstractService;
import com.lrmin.xkxt.coursergt.mapper.CourseRgtMapper;
import com.lrmin.xkxt.coursergt.entity.CourseRgt;

@Service 
public class CourseRgtService  extends AbstractService<CourseRgt, CourseRgtMapper>  {
	@Autowired
	private CourseRgtMapper mapper;

@Override
	public boolean exit(CourseRgt entity) {
		return findById(entity.getId()) == null;
	}

}