package com.lrmin.framework.service;

import java.util.List;


import com.lrmin.framework.entity.Entity;
import com.lrmin.framework.mapper.Dao;
import org.springframework.beans.factory.annotation.Autowired;

public abstract  class AbstractService<E extends Entity, M extends Dao<E>> implements Service<E> {
	
	@Autowired
	protected M mapper;

	@Override
	public Long insert(E entity){
		return mapper.insert(entity);
	}
	@Override
	public Long insertSelective(E entity){
		return mapper.insertSelective(entity);
	}

	@Override
	public Long update(E entity){
		return mapper.update(entity);
	}
	@Override
	public Long updateSelective(E entity){
		return mapper.updateSelective(entity);
	}

	@Override
	public Long deleteById(String string){
		return mapper.deleteById(string);
	}

	public Long deleteBy(E entity) {
		return mapper.deleteBy(entity);
	}
	@Override
	public E findById(String id){
		return mapper.findById(id).get(0);
	}
	@Override
	public List<E> findAll(){
		return mapper.findAll();
	}
	@Override
	public List<E> findBy(E entity){
		return mapper.findBy(entity);
	}

    public List<E> search(String search){
		return mapper.search(search);
	};
}
