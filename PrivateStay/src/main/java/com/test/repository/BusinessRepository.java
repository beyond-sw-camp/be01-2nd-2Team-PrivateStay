package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Business;

public interface BusinessRepository extends JpaRepository<Business, String> {

}
