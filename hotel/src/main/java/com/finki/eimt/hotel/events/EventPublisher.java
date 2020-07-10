package com.finki.eimt.hotel.events;

public interface EventPublisher {
    void publishEvent(DomainEvent event);
}
