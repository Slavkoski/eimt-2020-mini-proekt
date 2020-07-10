package com.finki.eimt.hotel.model;


import com.finki.eimt.hotel.model.price.Price;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table
public class Apartment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Room> rooms;

    private Integer numberOfRooms;

    private Integer numberOfBathrooms;

    private Integer numberOfBalconies;

    @Embedded
    private Price pricePerDay;

    public boolean isValid() {
        return ObjectUtils.allNotNull(rooms, numberOfRooms, numberOfBalconies, numberOfBathrooms, pricePerDay)
                && numberOfRooms > 0 && numberOfBalconies >= 0 && numberOfBathrooms >= 0 && rooms.stream().allMatch(Room::isValid);
    }
}
