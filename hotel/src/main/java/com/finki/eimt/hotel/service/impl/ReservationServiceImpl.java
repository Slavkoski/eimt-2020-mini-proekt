package com.finki.eimt.hotel.service.impl;


import com.finki.eimt.hotel.events.EventPublisher;
import com.finki.eimt.hotel.events.ReservationCreatedEvent;
import com.finki.eimt.hotel.events.ReservationDeletedEvent;
import com.finki.eimt.hotel.exceptions.ApartmentAlreadyReservedException;
import com.finki.eimt.hotel.model.Apartment;
import com.finki.eimt.hotel.model.Reservation;
import com.finki.eimt.hotel.model.User;
import com.finki.eimt.hotel.model.price.Price;
import com.finki.eimt.hotel.repository.ReservationRepository;
import com.finki.eimt.hotel.repository.UserRepository;
import com.finki.eimt.hotel.service.ApartmentService;
import com.finki.eimt.hotel.service.ReservationService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class ReservationServiceImpl implements ReservationService {
    private final ApartmentService apartmentService;
    private final UserRepository userRepository;
    private final ReservationRepository reservationRepository;
    private final EventPublisher eventPublisher;

    public ReservationServiceImpl(ApartmentService apartmentService, UserRepository userRepository, ReservationRepository reservationRepository, EventPublisher eventPublisher) {
        this.apartmentService = apartmentService;
        this.userRepository = userRepository;
        this.reservationRepository = reservationRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public List<Reservation> getAll() {
        return reservationRepository.findAll();
    }

    @Override
    public Reservation getById(Long id) {
        return reservationRepository.findById(id).orElse(null);
    }

    @Override
    public Reservation makeReservation(String firstName, String lastName, String idCardNumber, String email, String phoneNumber, List<Long> apartments, String dateFrom, String dateTo) throws ApartmentAlreadyReservedException {
        User user = User.builder().firstName(firstName)
                .lastName(lastName)
                .idCardNumber(idCardNumber)
                .email(email)
                .phoneNumber(phoneNumber)
                .build();
        user = userRepository.save(user);
        LocalDate localDateFrom = LocalDate.parse(dateFrom);
        LocalDate localDateTo = LocalDate.parse(dateTo);
        if (user.isValid() && localDateFrom != null && localDateTo != null && apartments != null && !apartments.isEmpty()) {
            List<Apartment> apartmentList = apartments
                    .stream()
                    .map(apartmentService::getApartmentById)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
            if (checkIfApartmentsAreAlreadyReserved(apartmentList, localDateFrom, localDateTo)) {
                throw new ApartmentAlreadyReservedException();
            }
            int numberOfDays = (int) ChronoUnit.DAYS.between(localDateFrom, localDateTo);
            Price totalPrice = new Price();
            for (Apartment apartment : apartmentList) {
                totalPrice = totalPrice.add(apartment.getPricePerDay().multiply(numberOfDays));
            }
            Reservation reservation = Reservation.builder().apartments(apartmentList).dateFrom(localDateFrom).dateTo(localDateTo).totalPrice(totalPrice).userId(user.getId()).build();
            if (reservation.isValid()) {
                Reservation tmpReservation = reservationRepository.save(reservation);
                eventPublisher.publishEvent(new ReservationCreatedEvent(tmpReservation, Instant.now()));
                return tmpReservation;
            }
            return null;
        }
        return null;
    }

    @Override
    public void delete(Long reservationId) {
        Optional<Reservation> reservation = reservationRepository.findById(reservationId);
        if (reservation.isPresent()) {
            reservationRepository.delete(reservation.get());
            eventPublisher.publishEvent(new ReservationDeletedEvent(reservation.get(), Instant.now()));
        }
    }

    private boolean checkIfApartmentsAreAlreadyReserved(List<Apartment> apartments, LocalDate dateFrom, LocalDate dateTo) {
        return apartments.stream().anyMatch(apartment -> apartmentService.isReserved(apartment, dateFrom, dateTo));
    }

}
