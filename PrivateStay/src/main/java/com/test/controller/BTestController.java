package com.test.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.test.entity.Business;
import com.test.service.BusinessService;

@RestController
@RequestMapping("/api/business")
public class BTestController {
	
	@Autowired
	private BusinessService businessService;
	
	//C
	@PostMapping
	public ResponseEntity<Business> saveB(@RequestBody Business business){
		Business saveB = businessService.saveB(business);
		return new ResponseEntity<>(saveB, HttpStatus.CREATED);
	}
	
	//R
	@GetMapping("/{id}")
    public ResponseEntity<Business> getBByCode(@PathVariable(value = "id") String bcode) {
		Business business = businessService.getBByCode(bcode);
        return ResponseEntity.ok().body(business);
    }
	
	
	@GetMapping("/list")
	@ResponseBody
	public List<Business> getAllB() {
	    return businessService.getAllB();
	}
	
	//U
	@PutMapping("/update/{id}")
	public ResponseEntity<Business> updateB(@PathVariable(value = "id") String bcode, @RequestBody Business business) {
		Business updatedB = businessService.updateB(bcode, business);
	    return ResponseEntity.ok(updatedB);
	}
	
	//D
	@DeleteMapping("/delete/{bcode}") 
	public ResponseEntity deleteBByCode(@PathVariable String bcode) {
		businessService.deleteBByCode(bcode);
		String m = bcode + "사업자 삭제가 성공적으로 완료되었습니다.";
		return new ResponseEntity<>(m, HttpStatus.OK);
	}
	
}
