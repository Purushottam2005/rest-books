package com.habuma.books;

public class ApiError {

	private int code;
	private String message;
	
	public ApiError(int code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public int getCode() {
		return code;
	}
	
	public String getMessage() {
		return message;
	}
	
}
