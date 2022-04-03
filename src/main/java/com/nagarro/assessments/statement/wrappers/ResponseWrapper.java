package com.nagarro.assessments.statement.wrappers;

import org.springframework.http.HttpStatus;

public class ResponseWrapper<T> {
	
	private HttpStatus status;
	private String message;
	private T data;
	
	public ResponseWrapper(HttpStatus status, String message, T data) {
		super();
		this.status = status;
		this.message = message;
		this.data = data;
	}
	
	
}
