package com.lrmin.xkxt.course.mapper;

import com.lrmin.framework.mapper.Dao;
import org.apache.ibatis.annotations.Mapper;
import java.util.List;
import com.lrmin.xkxt.course.entity.Course;

@Mapper 
public interface CourseMapper extends Dao<Course> {

}