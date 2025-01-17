package com.hotelBooking.Hotel.Repository;

import com.hotelBooking.Hotel.Models.Customers;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customers, Long> {
    Customers findByCustomerEmail(String customerEmail);
}
