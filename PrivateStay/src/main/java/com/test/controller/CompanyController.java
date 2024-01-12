package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.test.entity.Business;
import com.test.entity.Company;
import com.test.service.BusinessService;
import com.test.service.CompanyService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	@Autowired
	private BusinessService businessService;
	
	@GetMapping("/menu")
	public String showForm() {
		return "CforPrivate";
	}
	
	@GetMapping("/save")
	public String showSaveForm(Model model, HttpSession session) {
		String bId = (String) session.getAttribute("bId");
		
		if (bId != null) {
	        Business currentbusiness = businessService.getBusinessByCode(bId);
	        if (currentbusiness != null ) {
	        	Company newCompany = new Company();
	        	newCompany.setBusiness(currentbusiness);
	        	model.addAttribute("company", newCompany);
	        } else {
	        	model.addAttribute("error", "사업자 정보를 찾을 수 없습니다.");
	            return "redirect:/main/business/login";
	        	}	        
		} else {
			System.out.println("유효하지 않은 접근입니다.");
	    	return "redirect:/main/business/login"; //로그인 페이지로 이동
	    }
		return "Csave_form";
	}

	@PostMapping("/save")
	public String SaveCompany(@ModelAttribute Company company, Model model, HttpSession session) {
	    String bId = (String) session.getAttribute("bId");
	    
	    if (bId == null) {
	        System.out.println("사업자 인증이 필요합니다.");
	        return "redirect:/main/business/login"; 
	    }

	    try {
	        Business currentbusiness = businessService.getBusinessByCode(bId);
	        if (currentbusiness == null) {
	            model.addAttribute("error", "사업자 정보를 찾을 수 없습니다.");
	            return "redirect:/main/business/login";
	        }
	        company.setBusiness(currentbusiness);
	        companyService.saveCompany(company);
	        model.addAttribute("company", company);
	        model.addAttribute("message", "사업장 추가를 완료했습니다.");
	    } catch (RuntimeException e) {
	        model.addAttribute("error", e.getMessage());
	        System.out.println("사업장 추가 실패: " + e.getMessage());
	        return "Csave_form"; 
	    }
	    
	    return "Csave_form";
	}
	
}

//@PostMapping 			//http://localhost:8081/api/company
//public ResponseEntity<Company> saveCompany(@RequestBody Company company){
//	Company saveCompany = companyService.saveCompany(company);
//	return new ResponseEntity<>(saveCompany, HttpStatus.CREATED);
//}

//@GetMapping("{id}") 	//http://localhost:8081/api/department/2
//public ResponseEntity<Company> getCompanyByCode(@PathVariable("id") int companyCode){
//	Company company = companyService.getCompanyByCode(companyCode);
//	return ResponseEntity.ok(company);
//}
