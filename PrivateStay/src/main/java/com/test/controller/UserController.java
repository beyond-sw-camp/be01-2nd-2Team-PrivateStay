package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.User;
import com.test.service.UserService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/main/user/menu")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/profile")
    public String userProfileForm(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		
		if (userId != null) {
	        User currentUser = userService.getUserById(userId);
	        model.addAttribute("user", currentUser);
	    } else {
	        // 로그인하지 않은 경우의 처리
	    }
		
        return "updateUserProfile"; // Thymeleaf 템플릿 파일의 이름
    }
	
	
}
