package com.test.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class Homecontroller {
	
	@GetMapping
	public String indexForm() {
	    return "index";
	}
	
	@GetMapping("signup")
	public String signupForm() {
	    return "signUpForm";
	}
	
	@GetMapping("signin")
	public String signinForm() {
	    return "signInForm";
	}
	
}
