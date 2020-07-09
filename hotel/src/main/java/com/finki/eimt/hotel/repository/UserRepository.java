package com.finki.eimt.hotel.repository;

import com.finki.eimt.hotel.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
