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
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Business;
import com.test.service.BusinessService;

@RestController
@RequestMapping("api/business")
public class BusinessController {
	
	@Autowired
	private BusinessService businessService;
	
	@PostMapping
	public ResponseEntity<Business> saveBusiness(@RequestBody Business business) {
	
		Business saveBusiness = businessService.saveBusiness(business);
		
		return new ResponseEntity<>(saveBusiness, HttpStatus.CREATED);
	}
	
	@GetMapping("{id}") // http://localhost:8080/api/business/1 or 2 or ...
	public ResponseEntity<Business> getBusinessById(@PathVariable("id") Long businessId) {
		Business business =  businessService.getBusinessById(businessId);
		return ResponseEntity.ok(business);
	}
	
	@PutMapping("{id}")
    public ResponseEntity<Business> updateBusiness(@PathVariable("id") Long businessId,
                                                   @RequestBody Business updatedBusiness) {
        Business business = businessService.updateBusiness(businessId, updatedBusiness);
        if (business != null) {
            return ResponseEntity.ok(business);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteBusiness(@PathVariable("id") Long businessId) {
        businessService.deleteBusiness(businessId);
        return ResponseEntity.noContent().build();
    }
}
