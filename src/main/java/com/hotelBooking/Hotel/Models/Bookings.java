package com.hotelBooking.Hotel.Models;

import jakarta.persistence.*;

import java.sql.Date;
import java.util.List;

@Entity
@Table (name = "Bookings")
public class Bookings {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "booking_id")
    private long bookingId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customers customers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id", nullable = false)
    private Rooms rooms;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "package_id", nullable = false)
    private Package packages;

    @Column (name = "check_in_date")
    private Date checkInDate;

    @Column (name = "check_out_date")
    private Date checkOutdate;

    @Column (name = "status")
    private String status;

    @Column (name = "price")
    private int price;

    @OneToMany (mappedBy = "bookings", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Payment> payment;

    public long getBookingId() {
        return bookingId;
    }

    public void setBookingId(long bookingId) {
        this.bookingId = bookingId;
    }

    public Customers getCustomers() {
        return customers;
    }

    public void setCustomers(Customers customers) {
        this.customers = customers;
    }

    public Rooms getRooms() {
        return rooms;
    }

    public void setRooms(Rooms rooms) {
        this.rooms = rooms;
    }

    public Package getPackages() {
        return packages;
    }

    public void setPackages(Package packages) {
        this.packages = packages;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutdate() {
        return checkOutdate;
    }

    public void setCheckOutDate(Date checkOutdate) {
        this.checkOutdate = checkOutdate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Payment> getPayment() {
        return payment;
    }

    public void setPayment(List<Payment> payment) {
        this.payment = payment;
    }
}