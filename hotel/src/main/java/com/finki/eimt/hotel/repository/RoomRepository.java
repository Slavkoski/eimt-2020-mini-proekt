package com.finki.eimt.hotel.repository;

import com.finki.eimt.hotel.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room,Long> {
}
