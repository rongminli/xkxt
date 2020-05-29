package com.lrmin.xkxt.account.entity;

import com.lrmin.framework.entity.AbsctractEntity;

import java.util.Date;

/**
 * @author lirongmin
 * @date 2020/5/21 0021
 */
public class Account extends AbsctractEntity {
    private String name;
    private String nickname;
    private String password;
    private Date registrationAt;

    @Override
    public void init() {
        super.init();
        registrationAt = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getRegistrationAt() {
        return registrationAt;
    }

    public void setRegistrationAt(Date registrationAt) {
        this.registrationAt = registrationAt;
    }
}
