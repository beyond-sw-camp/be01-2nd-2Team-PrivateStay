package com.test.entity;

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
public class User {
	
	@Id
	@Column(name = "user_id", nullable = false)
	private String userId;
	
	@Column(name = "user_pwd", nullable = false)
	private String userPwd;
	
	@Column(name = "user_name", nullable = false)
	private String userName;
	
	@Column(name = "user_gender", nullable = false)
	private String userGender;
	
	@Column(name = "user_age", nullable = false)
	private int userAge;
	
	@Column(name = "user_reg_date", nullable = false)
	private String userRegDate;
	
	@OneToMany(mappedBy = "user", cascade={CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
	private List<Reservation> reservations = new ArrayList<>();
}
