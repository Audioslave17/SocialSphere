package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.config.JwtProvider;
import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.request.LoginRequest;
import com.example.demo.response.AuthResponse;
import com.example.demo.service.CustomUserDetailsService;
import com.example.demo.service.UserService;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;

@RestController
@RequestMapping("/auth")
public class AuthController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService coustomUserDetails;
	
	@PostMapping("/signup")
	public AuthResponse CteateUser(@RequestBody User user) throws Exception {
		
		User isExist = userRepository.findByEmail(user.getEmail());
		if(isExist!=null) {
			throw new Exception("This Email is already used with another account");
		}
		
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		
		User savedUser = userRepository.save(newUser);
		
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(),savedUser.getPassword());
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "sucessfull registered");
		
		return res;
	}
	//auth/signin
	@PostMapping("/signin")
	public AuthResponse signin(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticate(loginRequest.getEmail(),loginRequest.getPassword());
        
		String token = JwtProvider.generateToken(authentication);
		
		AuthResponse res = new AuthResponse(token, "login sucess");
		
		return res;
	}
	private Authentication authenticate(String email, String Password) {
		UserDetails userDetails = coustomUserDetails.loadUserByUsername(email);
		if(userDetails == null) {
			throw new BadCredentialsException("invalid Username");
		}
		if(!passwordEncoder.matches(Password, userDetails.getPassword())) {
			throw new BadCredentialsException("password not matching");
		}
		return new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
	}
}
