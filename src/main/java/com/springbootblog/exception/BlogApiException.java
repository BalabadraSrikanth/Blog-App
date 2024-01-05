package com.springbootblog.exception;

import org.springframework.http.HttpStatus;

public class BlogApiException  extends RuntimeException{
	private HttpStatus ststus;
	private String message;
	public BlogApiException(HttpStatus ststus, String message) {
		super();
		this.ststus = ststus;
		this.message = message;
	}
	public HttpStatus getStstus() {
		return ststus;
	}
	public void setStstus(HttpStatus ststus) {
		this.ststus = ststus;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
