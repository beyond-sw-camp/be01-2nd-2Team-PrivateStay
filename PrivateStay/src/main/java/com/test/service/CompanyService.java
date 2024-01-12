package com.test.service;

import com.test.entity.Company;

public interface CompanyService {
	
	Company saveCompany(Company company);							  // 1. 사업장 추가하기
	Company getCompanyByCode(int Ccode);							  // 2. 사업장 조회하기
	Company updateCompanyByCode(int Ccode, Company updatedCompany);	 			 // 3. 사업장 수정하기
	void deleteCompanyByCode(int Ccode);							  // 4. 사업장 삭제하기 
	
}
