package com.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Company;
import com.test.service.CompanyService;

@RestController
@RequestMapping("api/company")
public class CompanyController {
	
	@Autowired
	private CompanyService companyService;
	
	
	@PostMapping //http://localhost:8081/api/company
	public ResponseEntity<Company> saveCompany(@RequestBody Company company){
		Company saveCompany = companyService.saveCompany(company);
		return new ResponseEntity<>(saveCompany, HttpStatus.CREATED);
	}
	
	
	@GetMapping("{id}") //http://localhost:8081/api/department/2
	public ResponseEntity<Company> getCompanyByCode(@PathVariable("id") int companyCode){
		Company company = companyService.getCompanyByCode(companyCode);
		return ResponseEntity.ok(company);
	}
}
