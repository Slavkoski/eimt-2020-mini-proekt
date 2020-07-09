package com.finki.eimt.hotel.rest;

import com.finki.eimt.hotel.model.Room;
import com.finki.eimt.hotel.repository.RoomRepository;
import com.finki.eimt.hotel.service.RoomService;
import org.springframework.util.MimeTypeUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/rooms", produces = MimeTypeUtils.APPLICATION_JSON_VALUE)
public class RoomController {

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll(){
        return roomService.getAll();
    }

    @PostMapping
    public Room addRoom(@RequestParam Integer numberOfBeds){
        return numberOfBeds>0 ? roomService.save(numberOfBeds) : null;
    }
}
