package com.lrmin.framework.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;

public abstract class AbsctractEntity implements Entity, Serializable{
	protected String id;
	@DateTimeFormat(pattern = "yyyy-MM-ddThh:mm:ss:sss+ssss")
	protected Date createAt;
	private Date lastUpdateAt;
	private Integer version;

	public Date getLastUpdateAt() {
		return lastUpdateAt;
	}

	public void setLastUpdateAt(Date lastUpdateAt) {
		this.lastUpdateAt = lastUpdateAt;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public void init(){
		this.setCreateAt(new Date());
		this.setId(UUID.randomUUID().toString());
		this.version = 0;
	}

	@Override
	public String getId() {
		return id;
	}
	@Override
	public void setId(String id) {
		this.id = id;
	}

	@Override
	public Date getCreateAt() {
		return createAt;
	}
	@Override
	public void setCreateAt(Date createTime) {
		this.createAt = createTime;
	}

	@Override
	public String toString() {
		return " [id=" + id + ", creatTime=" + createAt + ", ";
	}
}
