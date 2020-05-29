package com.lrmin.framework.contoller.dto;

import com.lrmin.framework.entity.PageInfo;

public class BaseConditionEntityAndPageInfo<E> {
	private E conditionEntity;
	private PageInfo pageInfo;

	public  E getConditionEntity() {
		return conditionEntity;
	}

	public PageInfo getPageInfo() {
		return pageInfo;
	}
	
	public void setConditionEntity(E conditionEntity) {
		this.conditionEntity = conditionEntity;
	}

	public void setPageInfo(PageInfo pageInfo) {
		this.pageInfo = pageInfo;
	}

	@Override
	public String toString() {
		return "BaseConditionEntityAndPageInfo [conditionEntity=" + conditionEntity + ", pageInfo=" + pageInfo + "]";
	}

}
