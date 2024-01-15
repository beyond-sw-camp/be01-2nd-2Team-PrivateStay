package com.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
	
	@GetMapping("/list")
	public String listAllCompanies(Model model, HttpSession session) {
	    String bId = (String) session.getAttribute("bId");

	    if (bId == null) {
	        return "redirect:/business/login";
	    }

	    try {
	        List<Company> companies = companyService.findAllCompaniesByBusinessCode(bId);
	        model.addAttribute("companies", companies); // 조회한 사업장 리스트를 모델에 추가
	    } catch (Exception e) {
	        // 오류 처리
	        model.addAttribute("error", "사업장 조회 중 오류가 발생했습니다.");
	    }

	    return "Clist"; // 사업장 목록을 보여주는 뷰 페이지 이름
	}

	
	@GetMapping("/select")
	public String showSelectForm(HttpSession session) {
	    if (session.getAttribute("bId") == null) {
	        System.out.println("사업자 인증이 필요합니다.");
	        return "redirect:/business/login";
	    }
	    return "Cselect";
	}

	@GetMapping("/edit") // 사업장 수정 폼 보기
	public String updateCompanyForm(@RequestParam("company_code") int cCode, Model model, HttpSession session) {
	    String bId = (String) session.getAttribute("bId");
	    if (bId == null) {
	        return "redirect:/business/login"; // 로그인 페이지로 이동
	    }

	    Company company = companyService.getCompanyByCode(cCode);
	    if (company != null && company.getBusiness().getBusinessCode().equals(bId)) {
	        model.addAttribute("company", company);
	        return "Cupdate"; // 수정 폼 페이지로 이동
	    } else {
	        return "redirect:/company/menu"; // 권한 없음 혹은 사업장을 찾을 수 없음
	    }
	}

	@PostMapping("/edit/{cCode}") // 사업장 정보 업데이트
	public String updateCompany(@PathVariable int cCode, @ModelAttribute Company updatedCompany, HttpSession session, Model model) {
	    String bId = (String) session.getAttribute("bId");
	    if (bId == null) {
	        return "redirect:/business/login"; // 로그인 페이지로 이동
	    }

	    try {
	        Company existingCompany = companyService.getCompanyByCode(cCode);
	        if (!existingCompany.getBusiness().getBusinessCode().equals(bId)) {
	            model.addAttribute("error", "권한이 없습니다.");
	            return "redirect:/company/menu";
	        }

	        existingCompany.setCompany_name(updatedCompany.getCompany_name());
	        existingCompany.setCompany_addr(updatedCompany.getCompany_addr());
	        companyService.saveCompany(existingCompany);

	        model.addAttribute("company", existingCompany);
	        model.addAttribute("message", "사업장 정보가 업데이트되었습니다.");
	        return "Cupdate";
	    } catch (Exception e) {
	        model.addAttribute("error", "사업장 정보 업데이트에 실패했습니다.");
	        return "Cupdate";
	    }
	}
	
	@GetMapping(value = "/menu/{companyCode}")
	public String calenderForm(Model model, @PathVariable("companyCode") String companyCode, HttpSession session) {
		
		System.out.println(companyCode);
		if (companyCode != null) {
			model.addAttribute("companyCode", companyCode);
			session.setAttribute("companyCode", companyCode);
			//session.getAttribute("productCode");
			return "companyMenu";
		} else {
			System.out.println("유효하지 않은 접근입니다.");
			return "companyMenu";
		}
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
