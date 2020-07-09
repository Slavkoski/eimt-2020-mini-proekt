package com.finki.eimt.hotel.service.impl;

import com.finki.eimt.hotel.model.Room;
import com.finki.eimt.hotel.repository.RoomRepository;
import com.finki.eimt.hotel.service.RoomService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public List<Room> getAll() {
        return roomRepository.findAll();
    }

    @Override
    public Room getById(Long id) {
        return roomRepository.findById(id).orElse(null);
    }

    @Override
    public Room save(Integer numberOfBeds) {
        return roomRepository.save(Room.builder().numberOfBeds(numberOfBeds).build());
    }
}
