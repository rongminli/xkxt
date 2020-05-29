package com.lrmin.xkxt.userscourse.mapper;

import com.lrmin.xkxt.course.entity.Course;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.lrmin.framework.mapper.Dao;
import com.lrmin.xkxt.userscourse.entity.UsersCourses;

@Mapper 
public interface UsersCoursesMapper extends Dao<UsersCourses> {

    public List<Course> getCoursesByAccountId(String accountId);

    public List<Course> getCoursesByAccountIdRev(String accountId);

    public List<Course> conflictCheck(String courseId);
}