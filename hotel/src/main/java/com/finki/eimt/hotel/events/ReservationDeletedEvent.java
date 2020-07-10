package com.finki.eimt.hotel.events;

import com.finki.eimt.hotel.model.Reservation;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.Instant;


public class ReservationDeletedEvent extends ApplicationEvent implements DomainEvent {

    @Getter
    private final Reservation reservation;
    @Getter
    private final Instant occurredOn;

    public ReservationDeletedEvent(Reservation reservation, Instant occurredOn) {
        super(reservation);
        this.reservation = reservation;
        this.occurredOn = occurredOn;
    }

    @Override
    public Instant occurredOn() {
        return this.occurredOn;
    }


}
