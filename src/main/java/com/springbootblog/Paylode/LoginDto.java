package com.springbootblog.Paylode;

public class LoginDto {

	private String usernameoremail;
	private String password;
	public LoginDto() {
		super();
	}
	public LoginDto(String usernameoremail, String password) {
		super();
		this.usernameoremail = usernameoremail;
		this.password = password;
	}
	public String getUsernameoremail() {
		return usernameoremail;
	}
	public void setUsernameoremail(String usernameoremail) {
		this.usernameoremail = usernameoremail;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	
}
