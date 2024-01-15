package com.test.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.User;
import com.test.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/user")
public class UserLoginrController {
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/join")
    public String showJoinForm(Model model) {
        model.addAttribute("user", new User());
        return "joinUser_form"; // Thymeleaf 템플릿 파일의 이름
    }
	
	@PostMapping("/join") //http://localhost:8080/main/user/join
	public String joinUser(@ModelAttribute User user, Model model){
		LocalDate currentDate = LocalDate.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String formattedDate = currentDate.format(formatter);

		try {
			user.setUserRegDate(formattedDate);
			userService.joinUser(user);
			model.addAttribute("message", "가입이 완료되었습니다.");
			return "loginUser_form"; // 가입 성공시 로그인 페이지로 이동
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			System.out.println("회원 가입 실패");
			return "joinUser_form"; // 가입 실패 시 회원가입 폼을 다시 표시
		}
	}
	
	@GetMapping("/login")
	public String showLoginForm() {
	    return "loginUser_form";
	}
	
	@PostMapping("/login") //http://localhost:8080/main/user/login
	public String loginUser(@RequestParam String userid, @RequestParam String password, Model model, HttpSession session) {
		boolean isLogin = userService.loginUser(userid, password);
		
		if (isLogin) {
			session.setAttribute("userId", userid);
			System.out.println("session 정보: " + (String) session.getAttribute("userId"));
            return "forPrivateUser"; //로그인 성공시 유저 메뉴로 이동
        } else {
        	model.addAttribute("error", "로그인을 실패했습니다.");
            return "loginUser_form"; //로그인 실패 시 로그인 폼을 다시 표시
        }
	}
	
	@GetMapping("/logout")
	public String logoutUser(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/"; //로그인 페이지로 이동
	}
	
	@DeleteMapping("/delete/{userid}") //http://www.localhost.com:8080/user/delete/
	public ResponseEntity deleteUser(@PathVariable String userid) {
		userService.deleteUserById(userid);
		return new ResponseEntity<>("유저 삭제가 성공적으로 완료되었습니다.", HttpStatus.OK);
	}
}
