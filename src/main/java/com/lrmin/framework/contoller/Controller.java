package com.lrmin.framework.contoller;

import com.lrmin.framework.contoller.dto.BaseConditionEntityAndPageInfo;
import com.lrmin.framework.contoller.result.Result;
import com.lrmin.framework.entity.Entity;
import com.lrmin.framework.entity.PageInfo;

public interface Controller<E extends Entity>{
	/**
	 *新增实体
	 * @param entity
	 * @return
	 */
	Result add(E entity);
	/**
	 * 按照id删除
	 * @param id
	 * @return
	 */
	Result del(String id);
	/**
	 * 按照id更新
	 * @param condiEntity
	 * @return
	 */
	Result update(E condiEntity);
	/**
	 * 进行简单的组合查找
	 * @param condiEntity
	 * @return
	 */
	Result findBy(E condiEntity);

	Result findByPage(BaseConditionEntityAndPageInfo<E> cep) ;

	Result findByPage(E entity, PageInfo pageInfo) ;
}
