package com.hotelBooking.Hotel.Repository;

import com.hotelBooking.Hotel.Models.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository  extends JpaRepository<Admin, Long> {

    Admin findByUsername(String username);
    Admin findByRole(String role);

}
