package com.finki.eimt.hotel.events;

import com.finki.eimt.hotel.model.Reservation;
import com.finki.eimt.hotel.model.User;
import com.finki.eimt.hotel.repository.UserRepository;
import com.finki.eimt.hotel.service.EmailService;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class EventsListener {
    private static final String EMAIL_MESSAGE_CREATED_PATTERN = "Your reservation for apartment number %d is confirmed. \nTotal price will be %s.";
    private static final String EMAIL_MESSAGE_DELETED_PATTERN = "Your reservation for apartment number %d is deleted.";
    private final UserRepository userRepository;
    private final EmailService emailService;

    public EventsListener(UserRepository userRepository, EmailService emailService) {
        this.userRepository = userRepository;
        this.emailService = emailService;
    }

    @Async
    @EventListener
    public void onReservationCreatedEvent(ReservationCreatedEvent event) {
        Reservation reservation = event.getReservation();
        User user = userRepository.getOne(reservation.getUserId());
        String userEmail = user.getEmail();
        emailService.sendMail(userEmail, "Reservation confirmed", String.format(EMAIL_MESSAGE_CREATED_PATTERN, reservation.getId(), reservation.getTotalPrice()));
    }

    @EventListener
    public void onReservationDeletedEvent(ReservationDeletedEvent event) {
        Reservation reservation = event.getReservation();
        User user = userRepository.getOne(reservation.getUserId());
        String userEmail = user.getEmail();
        emailService.sendMail(userEmail, "Reservation deleted", String.format(EMAIL_MESSAGE_DELETED_PATTERN, reservation.getId()));
    }
}
