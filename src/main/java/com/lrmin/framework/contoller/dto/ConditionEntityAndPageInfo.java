package com.lrmin.framework.contoller.dto;

import com.lrmin.framework.entity.AbsctractEntity;
import com.lrmin.framework.entity.PageInfo;

public interface ConditionEntityAndPageInfo{
	/**
	 * 获取作为条件的实体对象
	 * @return
	 */
	<E extends AbsctractEntity> E getConditionEntity();
	
	/**
	 * 获取分页信息
	 * @return
	 */
	PageInfo getPageInfo();

}
	
	
