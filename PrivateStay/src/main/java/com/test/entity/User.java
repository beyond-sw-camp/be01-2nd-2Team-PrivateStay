package com.test.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	
	@Id
	private String user_id;
	
	private String user_pwd;
	private String user_name;
	private String user_gender;
	private int user_age;
	private String user_reg_date;
}
