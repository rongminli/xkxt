package com.lrmin.xkxt.userscourse.service;

import com.lrmin.xkxt.course.entity.Course;
import com.lrmin.xkxt.course.service.CourseService;
import com.lrmin.xkxt.coursergt.entity.CourseRgt;
import com.lrmin.xkxt.coursergt.service.CourseRgtService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.lrmin.framework.service.AbstractService;
import com.lrmin.xkxt.userscourse.mapper.UsersCoursesMapper;
import com.lrmin.xkxt.userscourse.entity.UsersCourses;

@Service 
public class UsersCoursesService  extends AbstractService<UsersCourses, UsersCoursesMapper>  {
	@Autowired
	private UsersCoursesMapper mapper;
	@Autowired
	private CourseService courseService;
	@Autowired
	private CourseRgtService courseRgtService;

	@Override
	public boolean exit(UsersCourses entity) {
		return findById(entity.getId()) == null;
	}

    public List<Course> getCoursesByAccountId(String accountId) {
		return mapper.getCoursesByAccountId(accountId);
    }

	public List<Course> getCoursesByAccountIdRev(String accountId) {
		return mapper.getCoursesByAccountIdRev(accountId);
	}

	public Set<Course> conflictCheck(String courseId, String accountId) {
		Set<Course> conflictCourses = new HashSet<Course>();

		Course thisCourse = courseService.findById(courseId);
		//找到这个课程的课程安排
		CourseRgt condi = new CourseRgt();
		condi.setCourseId(thisCourse.getId());
		List<CourseRgt> thisCourseRgts = courseRgtService.findBy(condi);

		List<Course> thisSelectCourses = this.getCoursesByAccountId(accountId);
		thisSelectCourses.stream().forEach( course -> {
			// 如果是同一个课程
			if (thisCourse.getId().equals(course.getId())) {
				conflictCourses.add(course);
				//检查课程安排是否冲突
			}else if(thisCourse.getStart() >= course.getStart() && thisCourse.getStart() <= course.getEnd() ||
					thisCourse.getEnd() >= course.getStart() && thisCourse.getEnd() <= course.getEnd() ) {
				//找到当前课程的课程安排
				CourseRgt condi_ = new CourseRgt();
				condi_.setCourseId(course.getId());
				List<CourseRgt> courseRgts = courseRgtService.findBy(condi_);

				//检查是否有冲突课程
				courseRgts.stream().forEach(courseRgt -> {
					thisCourseRgts.stream().forEach(thisCourseRgt -> {
						if (courseRgt.getWeekDay().equals(thisCourseRgt.getWeekDay())
							&& thisCourseRgt.getStart() >= courseRgt.getStart() && thisCourseRgt.getStart() <= courseRgt.getEnd()
							|| thisCourseRgt.getEnd() >= courseRgt.getStart() && thisCourseRgt.getEnd() <= courseRgt.getEnd()
						){
							conflictCourses.add(course);
						}
					});
				});
			}
		});


		return conflictCourses;
	}
}