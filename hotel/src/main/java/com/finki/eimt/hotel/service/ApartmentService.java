package com.finki.eimt.hotel.service;


import com.finki.eimt.hotel.model.Apartment;

import java.util.List;

public interface ApartmentService {
    List<Apartment> getAllApartments();
    Apartment getApartmentById(final Long id);
}
