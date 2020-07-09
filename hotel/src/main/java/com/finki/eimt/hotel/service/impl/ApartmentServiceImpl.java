package com.finki.eimt.hotel.service.impl;

import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.repository.ApartmentRepository;
import com.finki.eimt.hotel.service.ApartmentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository) {
        this.apartmentRepository = apartmentRepository;
    }

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getApartmentById(final Long id) {
        return apartmentRepository.getOne(id);
    }
}
