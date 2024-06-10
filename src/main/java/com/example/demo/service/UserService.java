package com.example.demo.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.models.User;

public interface UserService {

	public User registerUser(User usre);
	
	public User findUserById(Integer Id) throws Exception;
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer userId1, Integer userId2) throws Exception;
	
	public User updateUser(User user, Integer userId) throws Exception;
	
	public List<User> searchUser(String query);
	
	public User findUserByJwt(String jwt);
}
