package com.test.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Business {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "business_code", nullable = false)
	private String business_code;
	
	@Column(name = "business_pwd", nullable = false)
	private String business_pwd;
	
	@Column(name = "business_name", nullable = false)
	private String business_name;
	
	@Column(name = "business_reg_date", nullable = false)
	private LocalDate business_reg_date;
	
}
