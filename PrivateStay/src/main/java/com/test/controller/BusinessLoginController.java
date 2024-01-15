package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.test.entity.Business;
import com.test.service.BusinessService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/business")
public class BusinessLoginController {

	@Autowired
	private BusinessService businessService;

	@GetMapping("/save")
	public String showSaveForm(Model model) {
		model.addAttribute("business", new Business());
		return "Bsave_form";
	}

	@PostMapping("/save") // http://localhost:8080/main/business/join
	public String SaveBusiness(@ModelAttribute Business business, Model model) {
		try {
			businessService.saveBusiness(business);
			model.addAttribute("message", "가입이 완료되었습니다.");
			return "Blogin_form"; // 가입 성공시 로그인 페이지로 이동
		} catch (RuntimeException e) {
			model.addAttribute("error", e.getMessage());
			System.out.println("회원 가입 실패");
			return "Bsave_form"; // 가입 실패 시 회원가입 폼을 다시 표시
		}
	}

	@GetMapping("/login")
	public String showLoginForm() {
		return "Blogin_form";
	}

	@PostMapping("/login")
	public String Blogin(@RequestParam String bId, @RequestParam String bpwd, Model model, HttpSession session) {
		boolean isLogin = businessService.loginBusiness(bId, bpwd);

		if (isLogin) {
			session.setAttribute("bId", bId);
			System.out.println("session 정보: " + (String) session.getAttribute("bId"));			
			return "BforPrivate"; // 로그인 성공시 유저 메뉴로 이동
		} else {
			model.addAttribute("error", "로그인을 실패했습니다.");
			return "Blogin_form"; // 로그인 실패 시 로그인 폼을 다시 표시
		}
	}
	
	@GetMapping("/logout")
	public String logoutBusiness(HttpSession session) {
		session.invalidate(); // 세션 무효화
		return "redirect:/business/login"; //로그인 페이지로 이동
	}
	
	@GetMapping("/BforPrivate")
	public String showBforPrivate(Model model, HttpSession session) {
		String bId = (String) session.getAttribute("bId");
		
		if (bId != null) {
	        Business currentbusiness = businessService.getBusinessByCode(bId);
	        model.addAttribute("business", currentbusiness);
	    } else {
	    	System.out.println("유효하지 않은 접근입니다.");
	    	return "redirect:/main/business/login"; //로그인 페이지로 이동
	    }
        return "BforPrivate";
	}
}

/*
	 * @PostMapping // http://localhost:8080/api/business public
	 * ResponseEntity<Business> saveBusiness(@RequestBody Business business) {
	 * 
	 * Business saveBusiness = businessService.saveBusiness(business);
	 * 
	 * return new ResponseEntity<>(saveBusiness, HttpStatus.CREATED); }
	 * 
	 * 
	 * @GetMapping("{id}") // http://localhost:8080/api/business/id입력 public
	 * ResponseEntity<Business> getBusinessById(@PathVariable("id") String
	 * businessId) { Business business =
	 * businessService.getBusinessById(businessId); return
	 * ResponseEntity.ok(business); }
	 * 
	 * @PutMapping("{id}") // http://localhost:8080/api/business/id입력 public
	 * ResponseEntity<Business> updateBusiness(@PathVariable("id") String
	 * businessId,
	 * 
	 * @RequestBody Business updatedBusiness) { Business business =
	 * businessService.updateBusiness(businessId, updatedBusiness); if (business !=
	 * null) { return ResponseEntity.ok(business); } else { return
	 * ResponseEntity.notFound().build(); } }
	 * 
	 * @DeleteMapping("{id}") // http://localhost:8080/api/business/id입력 public
	 * ResponseEntity<Void> deleteBusiness(@PathVariable("id") String businessId) {
	 * businessService.deleteBusiness(businessId); return
	 * ResponseEntity.noContent().build(); }
	 */
