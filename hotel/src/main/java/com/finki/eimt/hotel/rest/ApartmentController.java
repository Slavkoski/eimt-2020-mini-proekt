package com.finki.eimt.hotel.rest;


import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.model.Room;
import com.finki.eimt.hotel.model.price.Price;
import com.finki.eimt.hotel.service.ApartmentService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import javax.persistence.OneToMany;
import java.util.List;

@RestController
@RequestMapping(path = "/apartments", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class ApartmentController {

    private final ApartmentService apartmentService;

    public ApartmentController(final ApartmentService apartmentService) {
        this.apartmentService = apartmentService;
    }

    @GetMapping
    public List<Apartment> getAll(){
        return apartmentService.getAllApartments();
    }

    @GetMapping(value = "/{id}")
    public Apartment getDetails(@PathVariable Long id){
        return apartmentService.getApartmentById(id);
    }

    @PostMapping
    public Apartment addApartment(@RequestParam Integer numberOfBathrooms,
                                  @RequestParam Integer numberOfBalconies,
                                  @RequestParam Long[] rooms,
                                  @RequestParam Double pricePerDay,
                                  @RequestParam String priceCurrency){
        return apartmentService.addApartment(rooms,numberOfBathrooms,numberOfBalconies,pricePerDay,priceCurrency);
    }
}
