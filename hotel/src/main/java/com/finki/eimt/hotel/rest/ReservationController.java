package com.finki.eimt.hotel.rest;


import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.model.Reservation;
import com.finki.eimt.hotel.service.ReservationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reservation")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(final ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<Reservation> getAll(){
        return reservationService.getAll();
    }

    @GetMapping(value = "/{id}")
    public Reservation getDetails(@PathVariable Long id){
        return reservationService.getById(id);
    }

    @PostMapping
    public Reservation makeReservation(@RequestParam String firstName,
                                       @RequestParam String lastName,
                                       @RequestParam String idCardNumber,
                                       @RequestParam String email,
                                       @RequestParam String phoneNumber,
                                       @RequestParam List<Long> apartments,
                                       @RequestParam String dateFrom,
                                       @RequestParam String dateTo) {
        return reservationService.makeReservation(firstName,lastName,idCardNumber,email,phoneNumber,apartments,dateFrom,dateTo);
    }

}
