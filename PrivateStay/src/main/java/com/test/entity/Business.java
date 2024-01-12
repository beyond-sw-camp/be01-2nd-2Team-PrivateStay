package com.test.entity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
	@Column(name = "business_code", nullable = false)
	private String business_code;
	
	@Column(name = "business_pwd", nullable = false)
	private String business_pwd;
	
	@Column(name = "business_name", nullable = false)
	private String business_name;
	
	@Column(name = "business_reg_date", nullable = false)
	private LocalDate business_reg_date;
	
	@OneToMany(mappedBy = "business", cascade = CascadeType.ALL, orphanRemoval = true)
        private List<Company> companies = new ArrayList<>();
	
}
