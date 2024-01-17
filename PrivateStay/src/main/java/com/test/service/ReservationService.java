package com.test.service;

import java.util.List;

import com.test.entity.Product;
import com.test.entity.Reservation;

public interface ReservationService {
	public void saveProduct(Reservation reservation, String userId, String sCode, 
			int year, int month, int day, int headCount, String status);
	public List<Reservation> searchByUserId(String userId);
	public List<Reservation> searchByProductCode(String productCode);
}
 