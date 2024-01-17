package com.test.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.test.entity.Company;
import com.test.entity.Product;
import com.test.entity.Reservation;
import com.test.entity.Stock;
import com.test.entity.User;
import com.test.service.ProductService;

import jakarta.persistence.EntityNotFoundException;

import com.test.repository.CompanyRepository;
import com.test.repository.ProductRepository;
import com.test.repository.ReservationRepository;
import com.test.repository.StockRepository;
import com.test.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;
 
@Service
@Slf4j
public class ReservationServiceImpl implements ReservationService {
	
	@Autowired
	private ReservationRepository reservationRepository;
	
	@Autowired
	private StockRepository stockRepository;
	
	@Autowired
	private UserRepository userRepository;

	public void saveProduct(Reservation reservation, String userId, String sCode, 
			int year, int month, int day, int headCount, String status) {
		Optional<User> optionalUser = userRepository.findById(userId);
		User user = optionalUser.orElseThrow(() -> new EntityNotFoundException("User not found for id: " + userId));

		Optional<Stock> optionalStock = stockRepository.findById(sCode);
		Stock stock = optionalStock.orElseThrow(() -> new EntityNotFoundException("Stock not found for id: " + sCode));
		
		reservation.setStock(stock);
		reservation.setUserId(userId);
		reservation.setUser(user);
		reservation.setDay(day);
		reservation.setMonth(month);
		reservation.setYear(year);
		reservation.setHeadcount(headCount);
		reservation.setStatus(status);
		
		reservationRepository.save(reservation);
	}
	public List<Reservation> searchByUserId(String userId){
		
		return reservationRepository.findByUserId(userId);
		//return productRepository.findByCompanyCompanyCode(companyCodeAsInt);
        
	}

}
