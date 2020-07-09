package com.finki.eimt.hotel.service.impl;

import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.model.Reservation;
import com.finki.eimt.hotel.model.Room;
import com.finki.eimt.hotel.model.price.Currency;
import com.finki.eimt.hotel.model.price.Price;
import com.finki.eimt.hotel.repository.ApartmentRepository;
import com.finki.eimt.hotel.repository.ReservationRepository;
import com.finki.eimt.hotel.service.ApartmentService;
import com.finki.eimt.hotel.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Transactional
public class ApartmentServiceImpl implements ApartmentService {

    private final ApartmentRepository apartmentRepository;
    private final ReservationRepository reservationRepository;
    private final RoomService roomService;

    public ApartmentServiceImpl(ApartmentRepository apartmentRepository, ReservationRepository reservationRepository, RoomService roomService) {
        this.apartmentRepository = apartmentRepository;
        this.reservationRepository = reservationRepository;
        this.roomService = roomService;
    }

    @Override
    public List<Apartment> getAllApartments() {
        return apartmentRepository.findAll();
    }

    @Override
    public Apartment getApartmentById(final Long id) {
        return apartmentRepository.getOne(id);
    }

    @Override
    public boolean isReserved(Apartment apartment, LocalDate dateFrom, LocalDate dateTo) {
        Reservation anyReservation = reservationRepository
                .findAll()
                .stream()
                .filter(reservation -> isOverlaping(reservation, dateFrom, dateTo))
                .filter(reservation -> reservation.containsApartment(apartment))
                .findAny().orElse(null);
        return anyReservation != null;
    }

    @Override
    public Apartment addApartment(Long[] rooms, Integer numberOfBathrooms, Integer numberOfBalconies, Double pricePerDay, String priceCurrency) {
        List<Room> roomList = Stream.of(rooms).map(roomService::getById).collect(Collectors.toList());
        if(!roomList.isEmpty()) {
            Apartment apartment = Apartment
                    .builder()
                    .rooms(roomList)
                    .numberOfRooms(roomList.size())
                    .numberOfBalconies(numberOfBalconies)
                    .numberOfBathrooms(numberOfBathrooms)
                    .pricePerDay(new Price(Currency.valueOf(priceCurrency), pricePerDay))
                    .build();
            return apartmentRepository.save(apartment);
        }
        return null;
    }

    private boolean isOverlaping(Reservation reservation, LocalDate dateFrom, LocalDate dateTo) {
        return Math.max(reservation.getDateFrom().toEpochDay(), dateFrom.toEpochDay()) < Math.min(reservation.getDateTo().toEpochDay(), dateTo.toEpochDay());
    }
}
