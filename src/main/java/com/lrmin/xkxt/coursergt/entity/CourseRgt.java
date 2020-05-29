package com.lrmin.xkxt.coursergt.entity;

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * @author lirongmin
 * @date 2020/5/23 0023
 */
public class CourseRgt extends AbsctractEntity {
    private String courseId;
    private Integer weekDay;
    private Integer start;
    private Integer end;
    private String classroom;

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public Integer getEnd() {
        return end;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }
}
