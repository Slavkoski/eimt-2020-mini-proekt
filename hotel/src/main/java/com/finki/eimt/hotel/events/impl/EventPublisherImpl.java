package com.finki.eimt.hotel.events.impl;

import com.finki.eimt.hotel.events.DomainEvent;
import com.finki.eimt.hotel.events.EventPublisher;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
public class EventPublisherImpl implements EventPublisher {
    private final ApplicationEventPublisher applicationEventPublisher;

    public EventPublisherImpl(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publishEvent(DomainEvent event) {
        applicationEventPublisher.publishEvent(event);
    }
}
