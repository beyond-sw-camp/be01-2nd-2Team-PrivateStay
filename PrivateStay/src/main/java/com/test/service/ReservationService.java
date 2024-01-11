package jpa.privatestay.domain.service;
import jpa.privatestay.domain.etc.ReservationSearch;

import jpa.privatestay.domain.etc.NotEnoughStockException;
import jpa.privatestay.domain.Reservation;
import jpa.privatestay.domain.Stock;
import jpa.privatestay.domain.User;
import jpa.privatestay.domain.repository.ReservationRepository;
import jpa.privatestay.domain.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationService {
    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;
    private final StockRepository stockRepository;
    //new order
    @Transactional
    public Integer reservation(String userId, Integer headCount, String status, Integer sCode) throws NotEnoughStockException {
        User user = userRepository.findOne(userId);
        Stock stock = stockRepository.findOne(sCode);

        Reservation reservation = Reservation.createReservation(user, headCount, status, stock);
        reservationRepository.save(reservation);
        return reservation.getId();
    }

    //cancel
    @Transactional
    public void cancelReservation(Integer reservId) {
        Reservation reservation = reservationRepository.findOne(reservId);
        reservation.cancelReservation();
    }

    //serachAll
    public List<Reservation> findReservations(ReservationSearch reservationSerach) {
        return reservationRepository.findAll(reservationSerach);
    }
}
