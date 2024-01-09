package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Integer>{ //Type, Key

}
