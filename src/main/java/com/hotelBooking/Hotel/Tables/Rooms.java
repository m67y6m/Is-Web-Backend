package com.hotelBooking.Hotel.Tables;

import jakarta.persistence.*;

@Entity
@Table(name = "rooms")
public class Rooms {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "room_id")
    private int roomId;

    @Column(name = "room_type")
    private String roomType;

    @Column(name = "room_price")
    private int roomPrice;

    @Column(name = "room_picture")
    private String roomPicture;

    @Column(name = "room_status" )
    private String roomStatus;


    public int getRoomId(){
        return roomId;
    }

    public void setRoomId (int roomId) {
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

}
