package com.finki.eimt.hotel.events;

import org.springframework.lang.NonNull;

import java.io.Serializable;
import java.time.Instant;

public interface DomainEvent extends Serializable {
    @NonNull
    Instant occurredOn();
}
