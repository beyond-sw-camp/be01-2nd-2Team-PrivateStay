package com.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Stock {

    @Id
    @Column(name = "stock_code")
    private String stockCode;
    
    @Column(name = "year")
    private int year;
    
    @Column(name = "month")
    private int month;
    
    @Column(name = "day")
    private int day;
    
    @Column(name = "stock_quantity")
    private boolean stockQuantity;

    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_code", referencedColumnName = "product_code")
    //@JoinColumn(name = "company_code")
    private Product product;
    
//    @Column(name = "product_code")
//    private int productCode;

//    @ManyToOne
//    @JoinColumn(name = "business_code2", referencedColumnName = "business_code")
//    private Business business;

}