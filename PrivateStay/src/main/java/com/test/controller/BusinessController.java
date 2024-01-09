package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Business;
import com.test.service.BusinessService;

@RestController
@RequestMapping("main/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@PostMapping 			// http://localhost:8080/api/business
	public ResponseEntity<Business> saveBusiness(@RequestBody Business business) {
	
		Business saveBusiness = businessService.saveBusiness(business);
		
		return new ResponseEntity<>(saveBusiness, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")		 // http://localhost:8080/api/business/id입력
	public ResponseEntity<Business> getBusinessById(@PathVariable("id") String businessId) {
		Business business =  businessService.getBusinessById(businessId);
		return ResponseEntity.ok(business);
	}
	
	@PutMapping("{id}")		 // http://localhost:8080/api/business/id입력
    public ResponseEntity<Business> updateBusiness(@PathVariable("id") String businessId,
                                                   @RequestBody Business updatedBusiness) {
        Business business = businessService.updateBusiness(businessId, updatedBusiness);
        if (business != null) {
            return ResponseEntity.ok(business);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")  // http://localhost:8080/api/business/id입력
    public ResponseEntity<Void> deleteBusiness(@PathVariable("id") String businessId) {
        businessService.deleteBusiness(businessId);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/login")
	public String loginUser(@RequestParam String bId, @RequestParam String bpwd) {
		boolean isLogin = businessService.loginBusiness(bId, bpwd);
		
		if (isLogin) {
            return "로그인을 성공했습니다."; 
        } else {
          
            return "아이디 또는 비밀번호가 올바르지 않습니다.";
        }
		
	}
}
