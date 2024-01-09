package com.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Company;
import com.test.service.CompanyService;
import com.test.repository.CompanyRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company saveCompany(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company getCompanyByCode(int companycode) {
		return companyRepository.findById(companycode).get();
	}
	
	@Override
	public void deleteCompanyByCode(int companycode) {
		companyRepository.deleteById(companycode);
	}

}
