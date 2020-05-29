package com.lrmin.xkxt.accontWx.entity;

import com.lrmin.framework.entity.AbsctractEntity;

/**
 * @author lirongmin
 * @date 2020/5/26 0026
 */
public class AccountWx extends AbsctractEntity {
    private String openId;
    private String accountId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }
}
