package com.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "company_code")
    private int companyCode;
    
    @Column(name = "business_code2")
    private String businessCode;
    
    @Column(name = "company_name")
    private String companyName;

    @Column(name = "company_addr")
    private String companyAddr;

//    @ManyToOne
//    @JoinColumn(name = "business_code2", referencedColumnName = "business_code")
//    private Business business;

}