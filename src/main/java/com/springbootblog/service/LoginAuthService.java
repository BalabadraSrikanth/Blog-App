package com.springbootblog.service;

import org.springframework.stereotype.Repository;

import com.springbootblog.Paylode.LoginDto;
import com.springbootblog.Paylode.RegisterDto;


public interface LoginAuthService {
	String login(LoginDto loginDto);
	String register(RegisterDto registerDto);
}
