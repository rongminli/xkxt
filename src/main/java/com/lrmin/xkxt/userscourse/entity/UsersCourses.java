package com.lrmin.xkxt.userscourse.entity;

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * @author lirongmin
 * @date 2020/5/24 0024
 */
public class UsersCourses extends AbsctractEntity {
    private String accountId;
    private String courseId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }
}
