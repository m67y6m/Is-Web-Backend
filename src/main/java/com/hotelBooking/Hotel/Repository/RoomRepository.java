package com.hotelBooking.Hotel.Repository;

import com.hotelBooking.Hotel.Tables.Rooms;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Rooms, Integer> {
}
