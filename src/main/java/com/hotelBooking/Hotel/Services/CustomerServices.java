package com.hotelBooking.Hotel.Services;

import com.hotelBooking.Hotel.Tables.Customers;
import com.hotelBooking.Hotel.Repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServices {

    @Autowired
    private CustomerRepository customerRepository;

    public Customers saveCustomer(Customers customer) {
        return customerRepository.save(customer);
    }

    public List<Customers> getAllCustomers() {
        return customerRepository.findAll();
    }

    public void deleteCustomer(int customerId) {
        customerRepository.deleteById((long) customerId);
    }

    public Customers findByCustomerEmail(String customerEmail) {
        return customerRepository.findByCustomerEmail(customerEmail);
    }
}
