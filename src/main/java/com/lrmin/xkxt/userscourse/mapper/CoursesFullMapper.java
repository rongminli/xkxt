package com.lrmin.xkxt.userscourse.mapper;

import org.apache.ibatis.annotations.Mapper; 
import java.util.List;
import com.lrmin.framework.mapper.Dao;
import com.lrmin.xkxt.userscourse.entity.CoursesFull;

@Mapper 
public interface CoursesFullMapper {
    List<CoursesFull> findUsers(String accountId);
}