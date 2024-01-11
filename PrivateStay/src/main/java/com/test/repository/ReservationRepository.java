package com.test.repository;

import  java.com.test.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(String userId);

    List<Reservation> findByReservationByUserIdandStatus(String userId, String status);


}

