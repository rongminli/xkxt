package com.lrmin.framework.service;

import java.util.List;

import org.springframework.beans.factory.InitializingBean;

public interface Service<E> {
	public Long insert(E entity);
	public Long insertSelective(E entity);

	public Long update(E entity);
	public Long updateSelective(E entity);

	public Long deleteById(String id);

	public E findById(String id);
	public List<E> findAll();
	public List<E> findBy(E entity);
	
	public boolean exit(E entity);
}
