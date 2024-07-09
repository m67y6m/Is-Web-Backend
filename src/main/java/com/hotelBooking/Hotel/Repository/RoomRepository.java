package com.hotelBooking.Hotel.Repository;

import com.hotelBooking.Hotel.Models.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoomRepository extends JpaRepository<Rooms, Long> {
    Optional<Rooms> findById(Long roomId);
}
