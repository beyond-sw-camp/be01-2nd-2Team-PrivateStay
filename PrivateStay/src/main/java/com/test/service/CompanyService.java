package com.test.service;

import java.util.List;

import com.test.entity.Company;

public interface CompanyService {
	
	Company saveCompany(Company company);							  // 1. 사업장 추가하기
	List<Company> findAllCompaniesByBusinessCode(String bId);// 2. 내 사업장 전체 조회하기
	Company getCompanyByCode(int Ccode);							  // 3. 사업장 선택 조회하기
	Company updateCompanyByCode(int Ccode, Company updatedCompany);	  // 4. 사업장 수정하기
	void deleteCompanyByCode(int Ccode);							  // 5. 사업장 삭제하기 
	
	List<Company> findAllCompanies(); //시스템의 전체 사업장 조회하기
	
	Company saveC(Company company);
	Company getCByCode(int cID);
	Company updateCByCode(int cID, Company company);
	void deleteCByCode(int cID);
	
}
