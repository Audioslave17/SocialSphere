package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
	
	@GetMapping
	public String HomeControllerHandler() {
		return "this is home controller handler";
	}
	@GetMapping("/home")
	public String HomeControllerHandler2() {
		return "this is home controller handler 2";
	}
	@GetMapping("/anurag")
	public String HomeControllerHandler3() {
		return "this is home controller handler 3";
	}
}
