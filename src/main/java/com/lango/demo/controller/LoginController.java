package com.lango.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("login")
public class LoginController {

	@PostMapping
	public String login(){
		return "login success";
	}
	
	@GetMapping
	public String logout() {
		return "logout";
	}
}
