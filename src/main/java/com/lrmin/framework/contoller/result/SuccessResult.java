package com.lrmin.framework.contoller.result;

public class SuccessResult extends Result{
	public SuccessResult() {
		this.setCode(20000);
		this.setMessage("请求完成");
	}
}
