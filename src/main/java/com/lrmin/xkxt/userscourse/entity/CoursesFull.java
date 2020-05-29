package com.lrmin.xkxt.userscourse.entity;

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * @author lirongmin
 * @date 2020/5/26 0026
 */
public class CoursesFull extends AbsctractEntity {
    private Integer weekDay;
    private Integer start;
    private Integer end;
    private String classroom;
    private String name;
    private String teacher;
    private Integer startRgt;
    private String courseId;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public Integer getStartRgt() {
        return startRgt;
    }

    public void setStartRgt(Integer startRgt) {
        this.startRgt = startRgt;
    }

    public Integer getEndRgt() {
        return endRgt;
    }

    public void setEndRgt(Integer endRgt) {
        this.endRgt = endRgt;
    }

    private Integer endRgt;
}
