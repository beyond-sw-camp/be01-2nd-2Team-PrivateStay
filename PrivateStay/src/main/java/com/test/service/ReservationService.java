package jpa.privatestay.domain.service;
import jpa.privatestay.domain.etc.NoSuchReservationException;
import jpa.privatestay.domain.etc._ReservationSearch;

import jpa.privatestay.domain.etc.NotEnoughStockException;
import jpa.privatestay.domain.Reservation;
import jpa.privatestay.domain.Stock;
import jpa.privatestay.domain.User;
import jpa.privatestay.domain.repository.ReservationRepository;
import jpa.privatestay.domain.repository.UserRepository;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
    public Integer reservation(String userId, Integer headCount, String status, Integer sCode) throws NotEnoughStockException {
        User user = userRepository.findOne(userId);
        Stock stock = stockRepository.findOne(sCode);

        Reservation reservation = Reservation.createReservation(user, headCount, status, stock);
        reservationRepository.save(reservation);
        return reservation.getId();
    }
    //cancel
    @Transactional
    public void cancelReservation(Integer reservId) throws  NoSuchReservationException {
        Optional<Reservation> optionalReservation = reservationRepository.findById(reservId);

        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.cancelReservation();
        } else {
            throw new NoSuchReservationException("no such reservation found");
        }

    }
    public List<Reservation> findReservationsByUserIdandStatus(String userId, String status) {
        return reservationRepository.findByReservationByUserIdandStatus(userId, status);
    }
    public List<Reservation> findByUserId(String userId) {
        return reservationRepository.findByUserId(userId);
    }

}
