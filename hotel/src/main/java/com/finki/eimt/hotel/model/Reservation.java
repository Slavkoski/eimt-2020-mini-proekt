package com.finki.eimt.hotel.model;

import com.finki.eimt.hotel.model.price.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {

    @Id
    private Long id;

    private Long userId;

    @OneToMany
    private List<Apartment> apartments;

    private LocalDate dateFrom;

    private LocalDate dateTo;

    private Price totalPrice;
}
