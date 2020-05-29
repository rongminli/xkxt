package com.lrmin.framework.contoller.result;

public class ErrorResult extends Result{
	public ErrorResult() {
		this.setCode(20001);
		this.setMessage("请求失败");
	}
}
