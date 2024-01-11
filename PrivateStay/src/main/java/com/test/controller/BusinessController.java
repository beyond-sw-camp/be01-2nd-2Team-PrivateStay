package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entity.Business;
import com.test.service.BusinessService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/main/business/menu")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/profile") // 사업자 프로필 보기
    public String businessProfileForm(Model model, HttpSession session) {
		String bId = (String) session.getAttribute("bId");
		
		if (bId != null) {
	        Business currentbusiness = businessService.getBusinessByCode(bId);
	        model.addAttribute("business", currentbusiness);
	    } else {
	    	System.out.println("유효하지 않은 접근입니다.");
	    	return "redirect:/main/business/login"; //로그인 페이지로 이동
	    }
		
        return "BupdateProfile"; // Thymeleaf 템플릿 파일의 이름
    }
	
	@PostMapping("/profile") //유저 프로필 보기
	public String BupdateProfile(@ModelAttribute Business updatedbusiness, HttpSession session, Model model) { 
		String bId = (String) session.getAttribute("bId");
		
		if (updatedbusiness.getBusiness_pwd() == null || updatedbusiness.getBusiness_pwd().isEmpty()) {
			model.addAttribute("error", "비밀번호는 공백이 될 수 없습니다.");
			return "BupdateProfile";
	    }
		
		try {
            updatedbusiness.setBusiness_code(bId); // 세션에서 받은 userId를 설정
            Business business = businessService.updateBusiness(bId, updatedbusiness);
            model.addAttribute("business", business);
            model.addAttribute("message", "프로필이 성공적으로 업데이트되었습니다.");
        } catch (Exception e) {
            model.addAttribute("error", "프로필 업데이트에 실패했습니다.");
        }

		return "BupdateProfile";
	}	
	
	/*
	 * @PostMapping("/profile") // 사업자 프로필 수정 public String
	 * BupdateProfile(@ModelAttribute Business updatedbusiness, HttpSession session,
	 * Model model) { String bId = (String) session.getAttribute("bId");
	 * 
	 * try { Business existingBusiness = businessService.getBusinessByCode(bId);
	 * 
	 * existingBusiness.setBusiness_pwd(updatedbusiness.getBusiness_pwd());
	 * 
	 * Business business = businessService.updateBusiness(bId, existingBusiness);
	 * 
	 * model.addAttribute("business", business); model.addAttribute("message",
	 * "프로필이 성공적으로 업데이트되었습니다."); } catch (Exception e) { model.addAttribute("error",
	 * "프로필 업데이트에 실패했습니다."); }
	 * 
	 * return "BupdateProfile"; }
	 */

}
