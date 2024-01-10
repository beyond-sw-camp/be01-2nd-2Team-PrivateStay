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
	
	@GetMapping("/profile") //유저 프로필 보기
    public String userProfileForm(Model model, HttpSession session) {
		String userId = (String) session.getAttribute("userId");
		
		if (userId != null) {
	        User currentUser = userService.getUserById(userId);
	        model.addAttribute("user", currentUser);
	    } else {
	    	System.out.println("유효하지 않은 접근입니다.");
	    	return "redirect:/main/user/login"; //로그인 페이지로 이동
	    }
		
        return "updateUserProfile"; // Thymeleaf 템플릿 파일의 이름
    }
	
	@PostMapping("/profile") //유저 프로필 보기
	public String updateUserProfile(@ModelAttribute User updatedUser, HttpSession session, Model model) { 
		String userId = (String) session.getAttribute("userId");
		
		if (updatedUser.getUser_pwd() == null || updatedUser.getUser_pwd().isEmpty()) {
			model.addAttribute("error", "비밀번호는 공백이 될 수 없습니다.");
			return "updateUserProfile";
	    }
		
		try {
            updatedUser.setUser_id(userId); // 세션에서 받은 userId를 설정
            User user = userService.updateUser(userId, updatedUser);
            model.addAttribute("user", user);
            model.addAttribute("message", "프로필이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            model.addAttribute("error", "프로필 업데이트에 실패했습니다.");
        }

		return "updateUserProfile";
	}
	
	

}
