package com.finki.eimt.hotel.service.impl;


import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.model.Reservation;
import com.finki.eimt.hotel.model.User;
import com.finki.eimt.hotel.service.ApartmentService;
import com.finki.eimt.hotel.service.ReservationService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class ReservationServiceImpl implements ReservationService {
    private final ApartmentService apartmentService;

    public ReservationServiceImpl(ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @Override
    public Reservation makeReservation(String firstName, String lastName, String idCardNumber, String email, String phoneNumber, List<Long> apartments, String dateFrom, String dateTo) {
        User user = User.builder().firstName(firstName)
                .lastName(lastName)
                .idCardNumber(idCardNumber)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        LocalDate localDateFrom=LocalDate.parse(dateFrom);
        LocalDate localDateTo=LocalDate.parse(dateTo);
        if (user.isValid() && localDateFrom!=null && localDateTo!=null && apartments!=null && !apartments.isEmpty()) {
            List<Apartment> apartmentList = apartments
                    .stream()
                    .map(apartmentService::getApartmentById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());


        }

        return null;
    }
}
