package com.test.controller;

import com.test.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserId(String userId);

    List<Reservation> findByReservationByUserIdandStatus(String userId, String status);


}

