package com.test.entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
// import jakarta.persistence.GeneratedValue;
// import jakarta.persistence.GenerationType;
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
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String business_code;
	
	private String business_pwd;
	private String business_name;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate business_reg_date;
	
}
