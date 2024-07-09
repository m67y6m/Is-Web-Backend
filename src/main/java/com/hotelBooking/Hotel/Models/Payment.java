package com.hotelBooking.Hotel.Models;

import jakarta.persistence.*;

@Entity
@Table (name = "payment")
public class Payment {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (name = "payment_id")
    private int paymentId;

    @Column (name = "payment_type")
    private String paymentType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "booking_id", nullable = false)
    private Bookings bookings;

    public int getPaymentId(){
        return paymentId;
    }

    public void setPaymentId (int paymentId) {
        this.paymentId = paymentId;
    }

    public String getPaymentType(){
        return paymentType;
    }

    public void setPaymentType (String paymentType) {
        this.paymentType = paymentType;
    }

    public Bookings getBookings() {
        return bookings;
    }

    public void setBookings(Bookings bookings) {
        this.bookings = bookings;
    }


}
