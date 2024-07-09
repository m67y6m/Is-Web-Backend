package com.hotelBooking.Hotel.Repository;


import com.hotelBooking.Hotel.Models.Package;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PackageRepository extends JpaRepository<Package, Integer> {
    Optional<Package> findById(Long id);
}
