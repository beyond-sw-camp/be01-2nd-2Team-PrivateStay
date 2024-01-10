package com.test.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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
	
	@NotNull
	@Column(nullable = false)
	private String user_pwd;
	
	@Column(nullable = false)
	private String user_name;
	
	@Column(nullable = false)
	private String user_gender;
	
	@Column(nullable = false)
	private int user_age;
	
	@Column(nullable = false)
	private String user_reg_date;
	
//	@OneToMany(mappedBy = "user")
//    private List<Reservation> reservations = new ArrayList<>();
}
