package com.lango.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lango.demo.service.impl.UserService;

@RestController("login")
public class LoginController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public String login(){
		return userService.login();
	}
	
	@GetMapping
	public String logout() {
		return "logout";
	}
}
