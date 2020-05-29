package com.lrmin.framework.entity;

import java.util.List;

import com.github.pagehelper.IPage;
import com.github.pagehelper.Page;

public class PageInfo implements IPage{
	private Integer pageNum;
	private Integer pageSize;
	private String orderBy;
	private long total;
	private Boolean loadNextPage;
	
	
	public void readPage(Page<?> page) {
		this.setTotal(page.getTotal());
	}
	
	public Boolean getLoadNextPage() {
		return loadNextPage;
	}
	public void setLoadNextPage(Boolean loadNext) {
		this.loadNextPage = loadNext;
	}
	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}
	public Integer getPageNum() {
		return pageNum;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public String getOrderBy() {
		return orderBy;
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	
}
