package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{

	List<Company> findByBusiness_BusinessCode(String businessCode);

}
