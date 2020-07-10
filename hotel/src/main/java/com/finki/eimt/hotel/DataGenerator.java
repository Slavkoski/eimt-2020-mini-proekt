package com.finki.eimt.hotel;

import com.finki.eimt.hotel.exceptions.ApartmentAlreadyReservedException;
import com.finki.eimt.hotel.service.ApartmentService;
import com.finki.eimt.hotel.service.ReservationService;
import com.finki.eimt.hotel.service.RoomService;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Component
public class DataGenerator {

    private final RoomService roomService;
    private final ApartmentService apartmentService;
    private final ReservationService reservationService;

    public DataGenerator(RoomService roomService, ApartmentService apartmentService, ReservationService reservationService) {
        this.roomService = roomService;
        this.apartmentService = apartmentService;
        this.reservationService = reservationService;
    }

    @PostConstruct
    @Transactional
    public void importData() throws ApartmentAlreadyReservedException {
        roomService.save(1);
        roomService.save(2);
        roomService.save(3);
        roomService.save(4);
        roomService.save(5);
        apartmentService.addApartment(new Long[]{1L, 2L}, 2, 2, 1600.0, "MKD");
        apartmentService.addApartment(new Long[]{3L}, 1, 1, 1100.0, "MKD");
        apartmentService.addApartment(new Long[]{4L}, 2, 1, 1400.0, "MKD");
        reservationService.makeReservation("Antonio", "Slavkoski", "A123456", "a.s@gmail.com", "070123456", Arrays.asList(1L, 2L), "2020-08-01", "2020-08-12");
    }
}
