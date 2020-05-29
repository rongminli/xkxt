package com.lrmin.framework.entity;

import java.util.Date;

public interface LogicDeleteable {
    public void logicDelete();
    boolean isLogicDeleted();

    public void setDeleteTime(Date deleteTime);
    public Date getDeleteTime();
}
