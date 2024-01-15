package com.test.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import com.test.exception.NotEnoughStockException;
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
    @JoinColumn(name = "userId")
    private User user;
    //**********************************
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
    //***************************************

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "sCode", referencedColumnName = "sCode")
    private Stock stock;

    //**********************************************
    private LocalDateTime currTimeStamp;
    private Integer headcount;

    public void setUser(User user) {
        this.user = user;
        user.getReservations().add(this);
    }


    public static Reservation createReservation(User user, Integer headcount, ReservationStatus status, Stock stock) throws NotEnoughStockException {
        boolean stockAvailability = stock.getQuantity();
        if (!stockAvailability) throw new NotEnoughStockException("no stock available");
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setStock(stock);
        stock.setQuantity(false);
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
        this.stock.setQuantity(true);
    }


}
