package com.test.entity;

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
public class User {
	
	@Id
	@Column(name = "user_id", nullable = false)
	private String user_id;
	
	@Column(name = "user_pwd", nullable = false)
	private String user_pwd;
	
	@Column(name = "user_name", nullable = false)
	private String user_name;
	
	@Column(name = "user_gender", nullable = false)
	private String user_gender;
	
	@Column(name = "user_age", nullable = false)
	private int user_age;
	
	@Column(name = "user_reg_date", nullable = false)
	private String user_reg_date;
	
//	@OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
//	private List<Reservation> reservations = new ArrayList<>();
}
