package com.lrmin.framework.mapper;

import java.util.List;

import com.lrmin.framework.entity.Entity;

public interface Dao<T extends Entity> {
	public Long insert(T entity);
	public Long insertSelective(T entity);

	public Long deleteById(String id); 
	public Long deleteBy(T entity);

	public Long update(T entity);
	public Long updateSelective(T entity);

	public List<T> findById(String id);
	public List<T> findAll();
	public List<T> findBy(T entity);

	List<T> search(String search);
}
