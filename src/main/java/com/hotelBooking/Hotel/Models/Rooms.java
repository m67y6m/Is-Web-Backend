package com.hotelBooking.Hotel.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "rooms")
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private Long roomId;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_price")
    private int roomPrice;

    @Column(name = "room_picture")
    private String roomPicture;

    @Column(name = "room_status" )
    private String roomStatus;

    @OneToMany (mappedBy = "rooms", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bookings> bookings;


    public Long getRoomId(){
        return roomId;
    }

    public void setRoomId (Long roomId) {
        this.roomId = roomId;
    }

    public String getRoomType(){
        return roomType;
    }

    public void setRoomType (String roomType) {
        this.roomType = roomType;
    }

    public int getRoomPrice(){
        return roomPrice;
    }

    public void setRoomPrice(int roomPrice){
        this.roomPrice = roomPrice;
    }

    public String getRoomStatus() {
        return roomStatus;
    }

    public  void  setRoomStatus(String roomStatus){
        this.roomStatus = roomStatus;
    }

    public String getRoomPicture() {
        return roomPicture;
    }

    public void setRoomPicture(String roomPicture){
        this.roomPicture = roomPicture;
    }

    public List<Bookings> getBookings() {
        return bookings;
    }

    public void setBookings(List<Bookings> bookings) {
        this.bookings = bookings;
    }

}
