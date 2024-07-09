package com.hotelBooking.Hotel.Services;

import com.hotelBooking.Hotel.Repository.PaymentRepository;
import com.hotelBooking.Hotel.Models.Payment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServices {

    @Autowired
    private PaymentRepository paymentRepository;

    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayment() {
        return paymentRepository.findAll();
    }

    public void deletePayment(int paymentId) {
        paymentRepository.deleteById((int) paymentId);
    }

}
