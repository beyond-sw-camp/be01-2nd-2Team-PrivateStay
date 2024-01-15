package com.test.controller;
import com.test.entity.Reservation;
import com.test.exception.NotEnoughStockException;
import com.test.service.ReservationSearch;
import com.test.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class ReservationController {

    private final ReservationService reservationService;

    @GetMapping("/user")
    public String createUserForm(Model model) {
        model.addAttribute(new UserForm());
        return "/user/userPage";
    }

    @PostMapping("/user")
    public String createUser(@Valid UserForm userForm, BindingResult bindingResult, HttpSession session) {
        if (bindingResult.hasErrors()) {
            return "/user/userPage";
        }
        session.setAttribute("userId", userForm.getUserId());
        session.setAttribute("sCode", userForm.getSCode());
        return "redirect:/reservation/new";

    }


    @GetMapping("/reservation/new")
    public String createForm(Model model, HttpSession session) {
        model.addAttribute(session.getAttribute("userId"));
        model.addAttribute(session.getAttribute("sCode"));
        System.out.println("userId in createForm = " + session.getAttribute("userId"));
        System.out.println("sCode in createForm = " + session.getAttribute("sCode"));
        model.addAttribute("reservationForm", new ReservationForm());
        return "reservation/reservationForm";

    }

    @PostMapping("/reservation/new")
    public String create(@Valid ReservationForm reservationForm, BindingResult bindingResult, HttpSession session) throws NotEnoughStockException {
        System.out.println("userId in create = " + session.getAttribute("userId"));
        System.out.println("sCode in create = " + session.getAttribute("sCode"));

        if (bindingResult.hasErrors()) {
            System.out.println("222");
            return "reservation/reservationForm";
        }
        reservationService.reservation((String) session.getAttribute("userId"), reservationForm.getHeadCount(), reservationForm.getStatus(), (String) session.getAttribute("sCode"));
        System.out.println("제대로동작중");
        return "redirect:/reservation";
    }


    @GetMapping("/reservation")
    public String reservationListByIdandStatus(@ModelAttribute("reservationSearch") ReservationSearch reservationSearch,
                                               Model model, HttpSession session) {
        reservationSearch.setUserId((String) session.getAttribute("userId"));
        List<Reservation> reservations = reservationService.findReservationsByUserIdandStatus(reservationSearch);
        model.addAttribute("reservations", reservations);
        return "reservation/reservationList";
    }

    @PostMapping("/reservation/cancel/{id}")
    public String cancelReservation(@PathVariable("id") Integer reservationId) { // 여기서는 reservation id를 찾는거임
        reservationService.cancelReservation(reservationId);
        return "redirect:/reservation"; // redirect to reservationList?
    }

    @GetMapping("/reservation/edit/{id}")
    public String updateReservationForm(@PathVariable("id") Integer reservationId, Model model, HttpSession session) {
        Reservation reservation = reservationService.findReservationById(reservationId);
        ReservationForm form = new ReservationForm();
        form.setStatus(reservation.getStatus());
        form.setHeadCount(reservation.getHeadcount());
        model.addAttribute("form", form);
        model.addAttribute("reservationId", reservationId);
        return "reservation/updateForm";
    }

    @PostMapping("/reservation/edit/{id}")
    public String updateReservation(@PathVariable("id") Integer reservationId, @ModelAttribute("form") ReservationForm form, HttpSession session) {
        Reservation updatedReservation = new Reservation();
        System.out.println("updated headcount is " + form.getHeadCount());
        updatedReservation.setStatus(form.getStatus());
        updatedReservation.setHeadcount(form.getHeadCount());
        reservationService.updateReservation(reservationId, updatedReservation);
        return "redirect:/reservation";
    }
}
