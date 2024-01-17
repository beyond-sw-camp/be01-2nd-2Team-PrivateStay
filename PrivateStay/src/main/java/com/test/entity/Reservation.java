package com.test.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.persistence.PrePersist;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reserv_id")
    private int reservId;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(nullable = false)
    private int year;

    @Column(nullable = false)
    private int month;

    @Column(nullable = false)
    private int day;

    @Column(nullable = false)
    private int headcount;

    @Column(name = "currTimeStamp", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date currTimeStamp;

    @Column(nullable = false)
    private String status;

    // stock_code와의 관계를 매핑합니다.
    @ManyToOne
    @JoinColumn(name = "stock_code")
    private Stock stock;

    // user_id와의 관계를 매핑합니다.
    @ManyToOne
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;

    // getters and setters

    @PrePersist
    protected void onCreate() {
        currTimeStamp = new Date();
    }
}