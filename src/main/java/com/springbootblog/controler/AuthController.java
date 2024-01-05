package com.springbootblog.controler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springbootblog.Paylode.LoginDto;
import com.springbootblog.Paylode.RegisterDto;
import com.springbootblog.service.ServiceImpl.LoginAuthServiceImpl;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	private LoginAuthServiceImpl loginAuthServiceImpl;

	public AuthController(LoginAuthServiceImpl loginAuthServiceImpl) {
		super();
		this.loginAuthServiceImpl = loginAuthServiceImpl;
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto){
		String response= loginAuthServiceImpl.login(loginDto);
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/signup")
	public ResponseEntity<String> register(@RequestBody RegisterDto registerDto){
		String response= loginAuthServiceImpl.register(registerDto);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}
}
