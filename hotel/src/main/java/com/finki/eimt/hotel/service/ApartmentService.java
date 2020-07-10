package com.finki.eimt.hotel.service;


import com.finki.eimt.hotel.model.Apartment;

import java.time.LocalDate;
import java.util.List;

public interface ApartmentService {
    List<Apartment> getAllApartments();

    Apartment getApartmentById(final Long id);

    boolean isReserved(Apartment apartment, LocalDate dateFrom, LocalDate dateTo);

    Apartment addApartment(Long[] rooms, Integer numberOfBathrooms, Integer numberOfBalconies, Double pricePerDay, String priceCurrency);

    boolean isReserved(Long id, String dateFrom, String dateTo);
}
