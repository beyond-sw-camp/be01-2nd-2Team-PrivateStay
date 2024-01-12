package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Company;
import com.test.repository.CompanyRepository;

// import lombok.extern.slf4j.Slf4j;

@Service
// @Slf4j
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company saveCompany(Company company) {
		if(companyRepository.findById(company.getCompany_code()).isPresent()) {
			throw new RuntimeException("이미 존재하는 사업장입니다.");
		}
		return companyRepository.save(company);
	}

	@Override
	public Company getCompanyByCode(int Ccode) {
		return companyRepository.findById(Ccode).get();
	}
	
	@Override
	public Company updateCompanyByCode(int Ccode, Company updatedCompany) {
		Company exisitingCompany = companyRepository.findById(Ccode).orElse(null);
		if (exisitingCompany != null) {
			exisitingCompany.setCompany_name(updatedCompany.getCompany_name());
			exisitingCompany.setCompany_addr(updatedCompany.getCompany_addr());
			return companyRepository.save(exisitingCompany);
		}
		return null;
	}
		
	@Override
	public void deleteCompanyByCode(int Ccode) {
		companyRepository.deleteById(Ccode);
	}

}
