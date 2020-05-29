package com.lrmin.xkxt.userinfo.entity;

/**
 * @author lirongmin
 * @date 2020/5/22 0022
 */

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * table users_info {
 *   id varchar(100)
 *   account_id varchar(100) [ref: - accounts.id]
 *   naem varchar(10)
 *   native_place varchar(100)
 *   phone varchar(20)
 *   email varchar(100)
 *   address varchar(200)
 *   avater varchar(100)
 *   last_update date
 *   create_at date
 *   version integer
 * }
 */
public class UserInfo extends AbsctractEntity {
    private String accountId;
    private String name;
    private String nativePlace;
    private String phone;
    private String email;
    private String address;
    private String avater;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAvater() {
        return avater;
    }

    public void setAvater(String avater) {
        this.avater = avater;
    }
}
