package jpa.privatestay.domain;
import jakarta.persistence.*;
import jpa.privatestay.domain.etc.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
@Getter
@Setter
public class Reservation {
    //******************************
    @Id
    @GeneratedValue
    @Column(name = "reserv_id")
    private Integer id;
//********************************************
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn(name = "user_id")
    private User user;
//**********************************
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
//***************************************
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="sCode")
    private Stock stock;
    //**********************************************
    private LocalDateTime currTimeStamp;
    private Integer headcount;
    public void setUser(User user) {
        this.user = user;
        user.getReservations().add(this);
    }
    public void setStock(Stock stock) {
        this.stock = stock;
        stock.setReservation(this);
    }

    public static Reservation createReservation(User user, Integer headcount, String status, Stock stock) throws NotEnoughStockException {
        boolean stockAvailability = stock.getQuantity();
        if (!stockAvailability) throw new NotEnoughStockException("no stock available");
        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setStock(stock);
        stock.setQuantity(false);
        reservation.setHeadcount(headcount);
        reservation.setCurrTimeStamp(LocalDateTime.now());
        //....?
        if (status.equals("on hold")) {
            reservation.setStatus(ReservationStatus.ON_HOLD);
        } else if (status.equals("reserved")) {
            reservation.setStatus(ReservationStatus.RESERVED);
        } else {
            reservation.setStatus(ReservationStatus.CANCELED);
        }
        return reservation;
    }
    public void cancelReservation() {
        this.setStatus(ReservationStatus.CANCELED);
        stock.setQuantity(true);
    }


}
