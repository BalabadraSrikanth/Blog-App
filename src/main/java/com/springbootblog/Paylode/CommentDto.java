package com.springbootblog.Paylode;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class CommentDto {


	private long id;
	@NotEmpty(message = "name Should not be null")
	private String name;
	@NotEmpty(message = "Email should not be null")
	@Email
	private String email;
	@NotEmpty(message = "Body should not be empty")
	@Size(message = "Atleast 10 charecters")
	private String body;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	
	
}
