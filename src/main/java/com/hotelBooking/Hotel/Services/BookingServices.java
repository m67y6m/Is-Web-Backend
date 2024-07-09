package com.hotelBooking.Hotel.Services;

import com.hotelBooking.Hotel.Models.Bookings;
import com.hotelBooking.Hotel.Models.Customers;
import com.hotelBooking.Hotel.Models.Package;
import com.hotelBooking.Hotel.Models.Rooms;
import com.hotelBooking.Hotel.Repository.BookingRepository;
import com.hotelBooking.Hotel.Repository.CustomerRepository;
import com.hotelBooking.Hotel.Repository.PackageRepository;
import com.hotelBooking.Hotel.Repository.RoomRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class BookingServices {

    @Autowired
    private BookingRepository bookingRepository;

    public List<Bookings> getBookingsByCustomerId(Long customerId) {
        return bookingRepository.findBookingsByCustomerId(customerId);
    }

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PackageRepository packageRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public void bookingToCustomers(Long customerId, Long roomId, Long packageId, String checkInDate, String checkOutDate, String status, int price) throws ParseException {
        // fetch rooms customers and packages entities
        Customers customers = customerRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer Not Found"));

        Rooms rooms = roomRepository.findById((long) Math.toIntExact(roomId))
                .orElseThrow(() -> new RuntimeException("Room Not Found"));

        Package packages = packageRepository.findById(Math.toIntExact(packageId))
                .orElseThrow(() -> new RuntimeException("Package Not Found"));

        // Create SimpleDateFormat object with the date format you expect
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

        // Parse the dates from String to java.util.Date
        Date checkIn = sdf.parse(checkInDate);
        Date checkOut = sdf.parse(checkOutDate);


        // Calculate the booking price
        int totalBookingPrice = rooms.getRoomPrice() + packages.getPackagePrice();

        // create and save bookings entities
        Bookings bookings = new Bookings();
        bookings.setCustomers(customers);
        bookings.setPackages(packages);
        bookings.setRooms(rooms);
        bookings.setCheckInDate(new java.sql.Date(checkIn.getTime()));
        bookings.setCheckOutDate(new java.sql.Date(checkOut.getTime()));
        bookings.setStatus(status);
        bookings.setPrice(totalBookingPrice);

        bookingRepository.save(bookings);
    }

    public List<Bookings> getAllBookings() {
        return bookingRepository.findAllBookings();
    }

    public Optional<Bookings> getBookingById(Long id) {
        return bookingRepository.findById(id);
    }

    public void updateBooking(Bookings booking) {
        bookingRepository.save(booking);
    }


}
