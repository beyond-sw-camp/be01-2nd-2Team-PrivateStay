package com.test.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.test.entity.Reservation;

public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

	List<Reservation> findByUserId(String userId);
	
	@Query("SELECT r FROM Reservation r WHERE SUBSTRING(r.stock.stockCode, LENGTH(r.stock.stockCode), 1) = :lastCharacter")
    List<Reservation> findByLastCharacterOfStockCode(String lastCharacter);
}

