package com.springbootblog.Paylode;

import java.util.Date;

public class ExceptionDetails {

	private Date timeDetail;
	private String message;
	private String Descripption;
	public ExceptionDetails(Date timeDetail, String message, String descripption) {
		super();
		this.timeDetail = timeDetail;
		this.message = message;
		Descripption = descripption;
	}
	public Date getTimeDetail() {
		return timeDetail;
	}
	public void setTimeDetail(Date timeDetail) {
		this.timeDetail = timeDetail;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getDescripption() {
		return Descripption;
	}
	public void setDescripption(String descripption) {
		Descripption = descripption;
	}
	
	
}
