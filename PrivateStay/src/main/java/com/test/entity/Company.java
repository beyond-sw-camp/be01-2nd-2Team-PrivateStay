package com.test.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
    @Column(name = "company_code", nullable = false)
    private int companyCode;
    
	@ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
	@JoinColumn(name = "business_code")
//  @Column(name = "business_code", nullable = false)
    private Business business;
    
    @Column(name = "company_name", nullable = false)
    private String company_name;

    @Column(name = "company_addr")
    private String company_addr;

    @Override
    public String toString() {
        return "Company{" +
               "companyCode=" + companyCode +
               ", business=" + (business != null ? business.getBusiness_code() : null) + // 연관 엔터티의 정보만 출력
               ", company_name='" + company_name + '\'' +
               ", company_addr='" + company_addr + '\'' +
               '}';
    }
    
}
