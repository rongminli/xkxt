package com.lrmin.framework.contoller;

import java.util.List;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.lrmin.framework.contoller.dto.BaseConditionEntityAndPageInfo;
import com.lrmin.framework.contoller.result.ErrorResult;
import com.lrmin.framework.contoller.result.Result;
import com.lrmin.framework.contoller.result.SuccessResult;
import com.lrmin.framework.entity.Entity;
import com.lrmin.framework.entity.PageInfo;
import com.lrmin.framework.service.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public abstract class AbstractController<E extends Entity, S extends Service<E>>
		implements Controller<E> {

	@Autowired
	protected S service;

	@Override
	@PostMapping("add")
	public Result add(E entity) {
		entity.init();
		service.insert(entity);
		return new SuccessResult().addData("entity", entity);
	}

	@Override
	@GetMapping("del")
	public Result del(String id) {
		if (StringUtils.isEmpty(id)) {
			return new ErrorResult().setMessage("参数不能为空");
		}

		Long re = service.deleteById(id);

		if (re == 0) {
			return new ErrorResult().setMessage("删除失败，没有找到相关数据");
		} else {
			return new SuccessResult().setMessage("删除成功");
		}
	}

	@Override
	@PostMapping("update")
	public Result update(E condEntity) {
		if (StringUtils.isEmpty(condEntity.getId())) {
			return new ErrorResult().setMessage("更新失败，主键不能为空");
		}

		Long re = service.updateSelective(condEntity);
		if (re == 0) {
			return new ErrorResult().setMessage("更新失败，没有找到相关数据");
		} else {
			return new SuccessResult().setMessage("更新完成");
		}
	}

	@Override
	@PostMapping("findBy")
	public Result findBy(E condiEntity) {
		List<E> result = service.findBy(condiEntity);
		return new SuccessResult().addData("entities", result);
	}

	@Override
	@PostMapping("findByPage")
	public Result findByPage(@RequestBody BaseConditionEntityAndPageInfo<E> cep) {
		E condiEntity = cep.getConditionEntity();
		PageInfo pageInfo = cep.getPageInfo();

		Page<E> pageResult = PageHelper.startPage(pageInfo);
		List<E> list = service.findBy(condiEntity);

		return new SuccessResult().addData("entitys", pageResult).addData("pageInfo", pageInfo);
	}

	@Override
	public Result findByPage(E entity, PageInfo pageInfo){
		Page<E> pageResult = PageHelper.startPage(pageInfo);
		List<E> list = service.findBy(entity);

		return new SuccessResult().addData("entitys", pageResult).addData("pageInfo", pageInfo);
	}
}
