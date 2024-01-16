package com.test.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Company;
import com.test.repository.CompanyRepository;

@Service
public class CompanyServiceImpl implements CompanyService{

	@Autowired
	private CompanyRepository companyRepository;
	
	@Override
	public Company saveCompany(Company company) {
		if(companyRepository.findById(company.getCompanyCode()).isPresent()) {
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

	@Override
	public List<Company> findAllCompaniesByBusinessCode(String bId) {
		return companyRepository.findByBusiness_BusinessCode(bId);
	}

	@Override
	public List<Company> findAllCompanies() {
		return companyRepository.findAll();
	}

	// 포스트맨
	
	@Override
	public Company saveC(Company company) {
		return companyRepository.save(company);
	}

	@Override
	public Company getCByCode(int cID) {
		return companyRepository.findById(cID).get();
	}

	@Override
	public Company updateCByCode(int cID, Company company) {
		Company updateC = getCByCode(cID);
		updateC.setCompany_name(company.getCompany_name());
		updateC.setCompany_addr(company.getCompany_addr());		
		return companyRepository.save(updateC);
	}

	@Override
	public void deleteCByCode(int cID) {
		companyRepository.deleteById(cID);
	}
}
