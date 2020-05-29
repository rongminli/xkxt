package com.lrmin.framework.entity;

import java.util.Date;

public interface Entity {
    public String getId();
    public void setId(String id);

    public Date getCreateAt();
    public void setCreateAt(Date createTime);

    public void init();
}
