package com.test.entity;
import java.time.LocalDateTime;

import com.test.exception.NotEnoughStockException;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
@Entity
@Table(name = "Reservation")
@Getter
@Setter
public class Reservation {
	 //******************************
    @Id
    @GeneratedValue
    @Column(name = "reservationId")
    private Integer id;
    //********************************************
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
    //**********************************
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    //***************************************

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "stock_code", referencedColumnName = "stock_code")
    private Stock stock;

    //**********************************************
    private LocalDateTime currTimeStamp;
    private Integer headcount;

    public void setUser(User user) {
        this.user = user;
        user.getReservations().add(this);
    }


    public static Reservation createReservation(User user, Integer headcount, ReservationStatus status, Stock stock) throws NotEnoughStockException {
        boolean stockAvailability = stock.isStockQuantity();
        if (!stockAvailability) throw new NotEnoughStockException("no stock available");
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setStock(stock);
        stock.setStockQuantity(false);
        reservation.setHeadcount(headcount);
        reservation.setCurrTimeStamp(LocalDateTime.now());
        if (status.equals(ReservationStatus.ON_HOLD)) {
            reservation.setStatus(ReservationStatus.ON_HOLD);
        } else if(status.equals(ReservationStatus.RESERVED)) {
            reservation.setStatus(ReservationStatus.RESERVED);
        } else {
            reservation.setStatus(ReservationStatus.CANCELED);
        }
        return reservation;
    }

    public void cancelReservation() {
        this.setStatus(ReservationStatus.CANCELED);
        this.stock.setStockQuantity(true);
    }

}
