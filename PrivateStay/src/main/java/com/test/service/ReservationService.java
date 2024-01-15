package com.test.service;
import com.test.entity.Reservation;
import com.test.entity.ReservationStatus;
import com.test.entity.Stock;
import com.test.entity.User;
import com.test.exception.NotEnoughStockException;
import com.test.repository.ReservationRepository;
import com.test.repository.UserRepository;
import com.test.repository.StockRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;

    //new order
    @Transactional
    public Integer reservation(String userId, Integer headCount, ReservationStatus status, String sCode) throws NotEnoughStockException {
        Optional<User> optionalUser = userRepository.findById(userId);
        User user = optionalUser.orElseThrow(() -> new NoSuchElementException("User not found with ID: " + userId));

        Optional<Stock> optionalStock = stockRepository.findById(sCode);
        Stock stock = optionalStock.orElseThrow(() -> new NoSuchElementException("Stock not found with ID: " + sCode));

        Reservation reservation = Reservation.createReservation(user, headCount, status, stock);
        reservationRepository.save(reservation);
        return reservation.getId();
    }
    //cancel
    @Transactional
    public void cancelReservation(Integer reservationId)  {
        Reservation reservation = findReservationById(reservationId);
        reservation.cancelReservation();
    }
    @Transactional
    public void updateReservation(Integer reservationId, Reservation param) {
        Reservation findedReservation = reservationRepository.findById(reservationId).get();
        findedReservation.setStatus(param.getStatus());
        findedReservation.setHeadcount(param.getHeadcount());
        findedReservation.setCurrTimeStamp(LocalDateTime.now());
    }
    public List<Reservation> findReservationsByUserIdandStatus(ReservationSearch reservationSearch) {
        return reservationRepository.findByUserIdAndStatus(reservationSearch.getUserId(), reservationSearch.getStatus());
    }
    public Reservation findReservationById(Integer reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).get();
        return reservation;
    }



}
