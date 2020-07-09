package com.finki.eimt.hotel.model;


import com.finki.eimt.hotel.model.price.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Apartment {

    @Id
    private Long id;

    @OneToMany
    private List<Room> rooms;

    private Integer numberOfRooms;

    private Integer numberOfBathrooms;

    private Integer numberOfBalconies;

    private Price pricePerDay;
}
