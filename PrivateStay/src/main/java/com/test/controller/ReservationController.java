package java.com.test.controller;

import java.com.test.entity.Reservation;
import java.com.test.entity.User;
import jpa.privatestay.domain.exception.NoSuchReservationException;
import jpa.privatestay.domain.exception.NotEnoughStockException;
import java.com.test.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {
    private final ReservationService reservationService;


    @GetMapping
    public String createForm(Model model) {
        model.addAttribute("reservationForm", new ReservationForm());

        return "reservation/reservationForm";

    }
    @PostMapping("/reservation")
    public String reservation(ReservationForm reservationForm, @RequestParam("status") String status,
                        @RequestParam("headcount") Integer headcount) throws NotEnoughStockException {
        reservationService.reservation(reservationForm.getUserId(), headcount, status, reservationForm.getSCode() );
        return "redirect:/reservations";
    }

    @GetMapping("/reservations")
    public String reservationList(Model model){
        List<Reservation> reservations = reservationService.findReservationsByUserIdandStatus((String) model.getAttribute("userId"),
                (String) model.getAttribute("status"));
        //findReservationsByUserIdandStatus
        model.addAttribute("reservations", reservations);

        return "reservation/reservationList";

    }

    @GetMapping("/reservations/{id}/cancel")
    public String cancelReservation(@PathVariable("id") Integer id) throws NoSuchReservationException {
        reservationService.cancelReservation(id);
        return "redirect:/reservations";
    }
}