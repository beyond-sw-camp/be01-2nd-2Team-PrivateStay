package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.User;
import com.test.service.UserService;

@RestController
@RequestMapping("/main/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@PostMapping("/join") //http://localhost:8080/main/join
	public ResponseEntity<?> joinUser(@RequestBody User user){
		try {
			User joinUser = userService.joinUser(user);
			String successMessage = "가입이 완료되었습니다.";
			return new ResponseEntity<>(successMessage, HttpStatus.CREATED); 
		} catch (RuntimeException e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping("/login")
	public String loginUser(@RequestParam String username, @RequestParam String password) {
		boolean isLogin = userService.loginUser(username, password);
		
		if (isLogin) {
            return "로그인을 성공했습니다."; 
        } else {
          
            return "아이디 또는 비밀번호가 올바르지 않습니다.";
        }
		
	}
}
