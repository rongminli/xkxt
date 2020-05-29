package com.lrmin.xkxt.userscourse.service;

import org.springframework.stereotype.Service; 
import org.springframework.beans.factory.annotation.Autowired;
import java.util.List;
import com.lrmin.xkxt.userscourse.mapper.CoursesFullMapper;
import com.lrmin.xkxt.userscourse.entity.CoursesFull;

@Service 
public class CoursesFullService {
	@Autowired
	private CoursesFullMapper coursesFullMapper;

	public  List<CoursesFull> findUsers(String accountId){
		return coursesFullMapper.findUsers(accountId);
	}
}