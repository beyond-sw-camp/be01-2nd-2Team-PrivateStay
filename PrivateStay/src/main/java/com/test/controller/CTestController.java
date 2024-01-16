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

import com.test.entity.Company;
import com.test.service.CompanyService;

@RestController
@RequestMapping("/api/company")
public class CTestController {
	
	@Autowired
	private CompanyService companyService;
	
	//C
	@PostMapping
	public ResponseEntity<Company> saveC(@RequestBody Company company){
		Company saveC = companyService.saveC(company);
		return new ResponseEntity<>(saveC, HttpStatus.CREATED);
	}
	
	//R
	@GetMapping("/{id}")
    public ResponseEntity<Company> getBByCode(@PathVariable(value = "id") int cID) {
		Company company = companyService.getCByCode(cID);
        return ResponseEntity.ok().body(company);
    }
	
	//U
	@PutMapping("/update/{id}")
	public ResponseEntity<Company> updateC(@PathVariable(value = "id") int cID, @RequestBody Company company) {
		Company updatedC = companyService.updateCByCode(cID, company);
	    return ResponseEntity.ok(updatedC);
	}
	
	//D
	@DeleteMapping("/delete/{cID}") 
	public ResponseEntity deleteCByCode(@PathVariable int cID) {
		companyService.deleteCByCode(cID);
		String m = cID + "사업장 삭제가 성공적으로 완료되었습니다.";
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
}
