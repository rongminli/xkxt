package com.lrmin.xkxt.userseduinfo.entity;

/**
 * @author lirongmin
 * @date 2020/5/22 0022
 */

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * table users_education_info {
 *   id varchar(100)
 *   user_id  varchar(100) [ref: - users_info.id]
 *   student_num varchar(100)
 *   grade integer
 *   college varchar(100)
 *   major varchar(50)
 *   _class varchar(50)
 *   last_update date
 *   create_at date
 *   version integer
 * }
 */
public class UserEduInfo extends AbsctractEntity {
    private String userId;
    private String student_num;
    private String grade;
    private String college;
    private String magor;
    private String className;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStudent_num() {
        return student_num;
    }

    public void setStudent_num(String student_num) {
        this.student_num = student_num;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getMagor() {
        return magor;
    }

    public void setMagor(String magor) {
        this.magor = magor;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

}
