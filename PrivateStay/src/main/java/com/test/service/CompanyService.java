package com.test.service;

import com.test.entity.Company;

public interface CompanyService {
	
	Company saveCompany(Company company);
	Company getCompanyByCode(int companycode);
	void deleteCompanyByCode(int companycode);
	
}
