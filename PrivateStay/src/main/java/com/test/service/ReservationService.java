//package com.test.service;
//import com.test.exception.NotEnoughStockException;
//import com.test.entity.Reservation;
//import com.test.entity.Stock;
//import com.test.entity.User;
//import com.test.repository.ReservationRepository;
//import com.test.repository.StockRepository;
//import com.test.repository.UserRepository;
//import org.springframework.transaction.annotation.Transactional;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@Transactional(readOnly = true)
//@RequiredArgsConstructor
//public class ReservationService {
//    private final ReservationRepository reservationRepository;
//    private final UserRepository userRepository;
//    private final StockRepository stockRepository;
//    //new order
//    @Transactional
//    public Integer reservation(String userId, Integer headCount, String status, String sCode) throws NotEnoughStockException {
//        User user = userRepository.findById(userId).get();
//        Stock stock = stockRepository.findById(sCode).get();
//        Reservation reservation = Reservation.createReservation(user, headCount, status, stock);
//        reservationRepository.save(reservation);
//        return reservation.getId();
//    }
//    //cancel
//    @Transactional
//    public void cancelReservation(Integer reservId)  {
//        Reservation reservation = reservationRepository.findById(reservId).get();
//
//    }
//    public List<Reservation> findReservationsByUserIdandStatus(String userId, String status) {
//        return reservationRepository.findByReservationByUserIdandStatus(userId, status);
//    }
//    public List<Reservation> findByUserId(String userId) {
//        return reservationRepository.findByUserId(userId);
//    }
//
//}
