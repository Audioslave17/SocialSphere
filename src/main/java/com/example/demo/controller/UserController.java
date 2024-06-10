package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserService userService;
//	@PostMapping("/users")
//	public User CteateUser(@RequestBody User user) {
////		User newUser = new User();
////		newUser.setEmail(user.getEmail());
////		newUser.setFirstName(user.getFirstName());
////		newUser.setLastName(user.getLastName());
////		newUser.setPassword(user.getPassword());
////		newUser.setId(user.getId());
//		
////		User savedUser = userRepository.save(newUser);
//		User savedUser = userService.registerUser(user);
//		return savedUser;
//	}
	
	
	@GetMapping("/api/users")
	public List<User> getUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	@GetMapping("/api/users/{userId}")
	public User getUserById(@PathVariable("userId")Integer id) throws Exception{
//		Optional<User> user = userRepository.findById(id);
//		if(user.isPresent()) {
//			return user.get();
//		}
//		throw new Exception("user does not exist with Userid "+ id);
		
		User user = userService.findUserById(id);
		return user;
		
	}
	
	
	@PutMapping("/api/users/{userId}")
	public User updateUser(@RequestHeader("Authorization")String jwt, @RequestBody User user) throws Exception {
		
//		Optional<User> user1 = userRepository.findById(userId);
//		if(user1.isEmpty()) {
//			throw new Exception("User by userId "+userId+" does not exist");
//		}
//		User oldUser = user1.get();
//		if(user.getFirstName()!=null) {
//			oldUser.setFirstName(user.getFirstName());
//		}
//		if(user.getLastName()!=null) {
//			oldUser.setLastName(user.getLastName());
//		}
//		if(user.getEmail()!=null) {
//			oldUser.setEmail(user.getEmail());
//		}
//		User updatedUser = userRepository.save(oldUser);
//		return updatedUser; 
		
		User reqUser = userService.findUserByJwt(jwt);
		
		User updatedUser = userService.updateUser(user, reqUser.getId());
		return updatedUser;
	}
	
	@DeleteMapping("users/{userId}")
	public String DeleteUser(@PathVariable("userId") Integer userId) throws Exception {
		
		Optional<User> user = userRepository.findById(userId);
		if(user.isEmpty()) {
			throw new Exception("User by userId \"+userId+\" does not exist");
		}
		userRepository.delete(user.get());
		
		return "User deleted sucessfully with id "+userId;
	}
	
	@PutMapping("/api/users/follow/{userId2}")
	public User followUserHandler(@RequestHeader("Authorization")String jwt, @PathVariable Integer userId2) throws Exception {
		User reqUser = userService.findUserByJwt(jwt);
		User user = userService.followUser(reqUser.getId(), userId2);
		return user;
	}
	
	@GetMapping("/api/users/search")
	public List<User> searchUser(@RequestParam("query") String query){
		List<User> users = userService.searchUser(query);
		return users;
	}
	
	@GetMapping("/api/users/profile")
	public User getUserFromToken(@RequestHeader("Authorization")String jwt) {
		User user = userService.findUserByJwt(jwt);
		user.setPassword(null);
		return user;
	}
}
