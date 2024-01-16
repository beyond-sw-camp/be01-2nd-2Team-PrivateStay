package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.test.entity.Reservation;
import com.test.entity.ReservationStatus;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUser_UserIdAndStatus(String userId, ReservationStatus status);


}

