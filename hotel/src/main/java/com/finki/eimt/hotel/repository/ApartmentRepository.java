package com.finki.eimt.hotel.repository;


import com.finki.eimt.hotel.model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment,Long> {
}
