package com.hotelBooking.Hotel.Services;

import com.hotelBooking.Hotel.Repository.RoomRepository;
import com.hotelBooking.Hotel.Models.Rooms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RoomServices {

    @Autowired
    private RoomRepository roomRepository;

    public List <Rooms> getAllRooms (){
        return roomRepository.findAll();
    }

    public Optional <Rooms> getRoomsById (int roomId) {
        return roomRepository.findById((long) roomId);
    }

    public Rooms saveRoom(Rooms rooms) {
        return roomRepository.save(rooms);
    }

    public void deleteRoom (int roomId)  {
        roomRepository.deleteById((long) roomId);
    }

}
