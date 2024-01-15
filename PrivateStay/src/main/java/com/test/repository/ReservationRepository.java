package com.test.repository;

import com.test.entity.Reservation;
import com.test.entity.ReservationStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    List<Reservation> findByUserIdAndStatus(String userId, ReservationStatus status);

}

