package com.lrmin.xkxt.course.entity;

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * @author lirongmin
 * @date 2020/5/22 0022
 */
public class Course extends AbsctractEntity {
    private String name;
    private String teacher;
    private Integer start;
    private Integer end;

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

}
