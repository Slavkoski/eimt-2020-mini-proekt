package com.finki.eimt.hotel.rest;


import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.service.ApartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/apartment")
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(final ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public List<Apartment> getAll(){
        return apartmentService.getAllApartments();
    }

    @GetMapping(name = "/{id}")
    public Apartment getDetails(@PathVariable Long id){
        return apartmentService.getApartmentById(id);
    }


}
