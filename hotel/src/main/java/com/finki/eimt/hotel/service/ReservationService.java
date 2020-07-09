package com.finki.eimt.hotel.service;

import com.finki.eimt.hotel.model.Reservation;

import java.util.List;

public interface ReservationService {
    List<Reservation> getAll();
    Reservation getById(Long id);
    Reservation makeReservation(String firstName, String lastName, String idCardNumber, String email, String phoneNumber, List<Long> apartments, String dateFrom, String dateTo);
}
