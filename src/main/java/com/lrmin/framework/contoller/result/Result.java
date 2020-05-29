package com.lrmin.framework.contoller.result;

import java.util.HashMap;
import java.util.Map;

public class Result {
	private int code;
	private String message;
	private Map<String,Object> data = new HashMap<String, Object>();
	
	public int getCode() {
		return code;
	}
	public Result setCode(int i) {
		this.code = i;
		return this;
	}
	public String getMessage() {
		return message;
	}
	public Result setMessage(String message) {
		this.message = message;
		return this;
	}
	public Map<String, Object> getData() {
		return data;
	}
	public Result addData(String key,Object value) {
		this.data.put(key, value);
		return this;
	}
}
