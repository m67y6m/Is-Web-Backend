package com.hotelBooking.Hotel.Repository;

import com.hotelBooking.Hotel.Models.Bookings;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookingRepository extends JpaRepository<Bookings, Long> {

    @Query("SELECT b FROM Bookings b JOIN FETCH b.rooms JOIN FETCH b.packages JOIN FETCH b.customers")
    List<Bookings> findAllBookings();

    @Query("SELECT b FROM Bookings b JOIN FETCH b.rooms JOIN FETCH b.packages JOIN FETCH b.customers WHERE b.customers.customerId = :customerId")
    List<Bookings> findBookingsByCustomerId(Long customerId);
}
