package com.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.User;

public interface UserRepository extends JpaRepository<User, String>{
	
}
