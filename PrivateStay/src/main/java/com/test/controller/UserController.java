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


@Controller
@RequestMapping("/main/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("user", new User());
        return "joinUser_form"; // Thymeleaf 템플릿 파일의 이름
    }
	
	@PostMapping("/join") //http://localhost:8080/main/user/join
	public String joinUser(@ModelAttribute User user, Model model){
		try {
			userService.joinUser(user);
			model.addAttribute("message", "가입이 완료되었습니다.");
			return "forPrivateUser"; // 가입 성공 페이지로 이동
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			return "joinUser_form"; // 가입 실패 시 회원가입 폼을 다시 표시
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
