package com.hotelBooking.Hotel.Repository;

import com.hotelBooking.Hotel.Models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}
