package com.springbootblog.service.ServiceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.springbootblog.Paylode.LoginDto;
import com.springbootblog.Paylode.RegisterDto;
import com.springbootblog.entity.Role;
import com.springbootblog.entity.User;
import com.springbootblog.exception.BlogApiException;
import com.springbootblog.repository.RoleRepo;
import com.springbootblog.repository.UserRepo;
import com.springbootblog.service.LoginAuthService;

@Service
public class LoginAuthServiceImpl implements LoginAuthService {

	private AuthenticationManager authenticationManaget;
	private UserRepo userRepo;
	private RoleRepo roleRepo;
	private PasswordEncoder passwordEncoder;
	
	

	public LoginAuthServiceImpl(AuthenticationManager authenticationManaget, UserRepo userRepo, RoleRepo roleRepo,
			PasswordEncoder passwordEncoder) {
		super();
		this.authenticationManaget = authenticationManaget;
		this.userRepo = userRepo;
		this.roleRepo = roleRepo;
		this.passwordEncoder = passwordEncoder;
	}



	@Override
	public String login(LoginDto loginDto) {
		Authentication authentiacation = authenticationManaget.authenticate(new UsernamePasswordAuthenticationToken(
				loginDto.getUsernameoremail(), loginDto.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentiacation);
		
		return "user Login Sucessful";
	}


	@Override
	public String register(RegisterDto registerDto) {
		// checking username preveosly existed
				if(userRepo.existsByUsername(registerDto.getUsername())) {
					throw new BlogApiException(HttpStatus.BAD_REQUEST, "username already exist");
				}
				// checking email preveosly existed
				if(userRepo.existsByEmail(registerDto.getEmail())) {
					throw new BlogApiException(HttpStatus.BAD_REQUEST, "Email already exist");
				}
				
				User user = new User();
				user.setName(registerDto.getName());
				user.setEmail(registerDto.getEmail());
				user.setUsername(registerDto.getUsername());
				user.setName(registerDto.getName());
				user.setPassword( passwordEncoder.encode(registerDto.getPassword()));
				
			Set<Role> role=new HashSet<>();
			Role userRole=roleRepo.findByName("ROle_User").get();
			role.add(userRole);
			user.setRoles(role);
			userRepo.save(user);
				return "User Sucessfully Registerd";	}

}
