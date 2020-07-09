package com.finki.eimt.hotel.service;

import com.finki.eimt.hotel.model.Room;

import java.util.List;

public interface RoomService {
    List<Room> getAll();
    Room getById(Long id);
    Room save(Integer numberOfBeds);
}
