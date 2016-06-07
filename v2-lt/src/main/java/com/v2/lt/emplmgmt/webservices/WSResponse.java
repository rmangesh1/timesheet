package com.v2.lt.emplmgmt.webservices;

public class WSResponse<T> {
	
	private T obj;
	
	private String errorResponse;

	public T getObj() {
		return obj;
	}

	public void setObj(T obj) {
		this.obj = obj;
	}

	public String getErrorResponse() {
		return errorResponse;
	}

	public void setErrorResponse(String errorResponse) {
		this.errorResponse = errorResponse;
	}
	
}
