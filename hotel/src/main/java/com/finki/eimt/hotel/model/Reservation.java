package com.finki.eimt.hotel.model;

import com.finki.eimt.hotel.model.price.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long userId;

    @OneToMany
    private List<Apartment> apartments;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Price totalPrice;

    public boolean isValid() {
        return ObjectUtils.allNotNull(userId,apartments,dateFrom,dateTo,totalPrice)
                && !apartments.isEmpty();
    }

    public boolean containsApartment(Apartment apartment){
        return apartments.contains(apartment);
    }

    public boolean containsApartment(Long apartmentId){
        return apartments.stream().map(Apartment::getId).collect(Collectors.toList()).contains(apartmentId);
    }
}
