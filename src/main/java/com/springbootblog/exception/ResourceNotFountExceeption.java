package com.springbootblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFountExceeption extends RuntimeException{
	private String resourceName;
	private long fieldvalue;
	private String fieldname;
	
	
	public ResourceNotFountExceeption() {
		super();
	}
	public ResourceNotFountExceeption(String resourceName, long fieldvalue, String fieldname) {
		super(String.format("%s not found with %s : '%s'",resourceName,fieldvalue,fieldname));
		this.resourceName = resourceName;
		this.fieldvalue = fieldvalue;
		this.fieldname = fieldname;
	}
	public String getResourceName() {
		return resourceName;
	}
	public long getFieldvalue() {
		return fieldvalue;
	}
	public String getFieldname() {
		return fieldname;
	}
	
	
}
