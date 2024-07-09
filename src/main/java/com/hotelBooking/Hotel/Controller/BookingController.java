package com.hotelBooking.Hotel.Controller;

import com.hotelBooking.Hotel.Models.Bookings;
import com.hotelBooking.Hotel.Services.BookingServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import static java.lang.String.format;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingServices bookingServices;

    @GetMapping("/getByCustomerId/{customerId}")
    public ResponseEntity<List<Bookings>> getBookingsByCustomerId(@PathVariable Long customerId) {
        List<Bookings> bookings = bookingServices.getBookingsByCustomerId(customerId);
        return ResponseEntity.ok(bookings);
    }

//    @PostMapping("/add")
//    public ResponseEntity<String> addBooking(@RequestBody Map<String, Object> payload) throws ParseException {
//        String customerId = (String) payload.get("customerId");
//        String roomId = (String) payload.get("roomId");
//        String packageId = (String) payload.get("packageId");
//        String checkInDateStr = (String) payload.get("checkInDate");
//        String checkOutDateStr = (String) payload.get("checkOutDate");
//        Integer price = (Integer) payload.get("price");
//        String status = "PENDING";
//
//        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");  // Adjust format if needed
//
//        String checkInDate = format(checkInDateStr);
//        String checkOutDate = format(checkOutDateStr);
//
//        try {
//            bookingServices.bookingToCustomers(Long.valueOf(customerId), Long.valueOf(roomId), Long.valueOf(packageId), checkInDate, checkOutDate, status, price);
//            return ResponseEntity.ok("Booking added successfully");
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body("Invalid data format or error adding booking.");
//        }
//    }

    @PostMapping("/add")
    public ResponseEntity<String> addBooking(@RequestBody Map<String, Object> payload) {
        Long customerId = ((Number) payload.get("customerId")).longValue();
        Long roomId = ((Number) payload.get("roomId")).longValue();
        Long packageId = ((Number) payload.get("packageId")).longValue();
        String checkInDate = (String) payload.get("checkInDate");
        String checkOutDate = (String) payload.get("checkOutDate");
        int price = ((Number) payload.get("price")).intValue();
        String status = "PENDING";  // Set status to pending

        try {
            bookingServices.bookingToCustomers(customerId, roomId, packageId, checkInDate, checkOutDate, status, price);
            return ResponseEntity.ok("Booking added successfully");
        } catch (ParseException e) {
            return ResponseEntity.badRequest().body("Invalid date format. Please use dd/MM/yyyy.");
        }
    }



    @GetMapping("/get")
    public ResponseEntity<List<Bookings>> getAllBookings() {
        List<Bookings> bookings = bookingServices.getAllBookings();
        return ResponseEntity.ok(bookings);
    }

    @PutMapping("/pay/{id}")
    public ResponseEntity<String> makePayment(@PathVariable Long id) {
        Optional<Bookings> bookingOptional = bookingServices.getBookingById(id);
        if (bookingOptional.isPresent()) {
            Bookings booking = bookingOptional.get();
            if ("approved".equalsIgnoreCase(booking.getStatus())) {
                booking.setStatus("PAID");
                bookingServices.updateBooking(booking);
                return ResponseEntity.ok("Booking paid successfully");
            } else {
                return ResponseEntity.badRequest().body("Booking is not in a approved state");
            }
        } else {
            return ResponseEntity.badRequest().body("Booking not found");
        }
    }

    @PutMapping("/approve/{id}")
    public ResponseEntity<String> approveBooking(@PathVariable Long id) {
        Optional<Bookings> bookingOptional = bookingServices.getBookingById(id);
        if (bookingOptional.isPresent()) {
            Bookings booking = bookingOptional.get();
            if ("pending".equalsIgnoreCase(booking.getStatus())) {
                booking.setStatus("APPROVED");
                bookingServices.updateBooking(booking);
                return ResponseEntity.ok("Booking approved successfully");
            } else {
                return ResponseEntity.badRequest().body("Booking is not in a pending state");
            }
        } else {
            return ResponseEntity.badRequest().body("Booking not found");
        }
    }

    @PutMapping("/cancel/{id}")
    public ResponseEntity<String> cancelBooking(@PathVariable Long id) {
        Optional<Bookings> bookingsOptional = bookingServices.getBookingById(id);
        if (bookingsOptional.isPresent()) {
            Bookings bookings = bookingsOptional.get();
            if("pending".equalsIgnoreCase(bookings.getStatus())) {
                bookings.setStatus("CANCELLED");
                bookingServices.updateBooking(bookings);
                return ResponseEntity.ok("Booking Cancelled Sucessfully");
            } else {
                return ResponseEntity.badRequest().body("Booking is not in a cancelling state");
            }
        } else {
            return ResponseEntity.badRequest().body("Booking not found");
        }
    }

}
