package com.hotelBooking.Hotel.Controller;

import com.hotelBooking.Hotel.Services.CustomerServices;
import com.hotelBooking.Hotel.Models.Customers;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerServices customerServices;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> add(@RequestBody Customers addCustomer) {
        Customers existCustomer = customerServices.findByCustomerEmail(addCustomer.getCustomerEmail());
        Map<String, String> response = new HashMap<>();
        if (existCustomer != null) {
            response.put("message", "Email already existed");
            return ResponseEntity.badRequest().body(response);
        }
        addCustomer.setCustomerStatus("Active");
        customerServices.saveCustomer(addCustomer);
        response.put("message", "Registration succeeded");
        return ResponseEntity.ok(response);
    }

    @PostMapping("/customerLogIn")
    public ResponseEntity<Map<String, String>> customerLogIn(@RequestBody Customers customers, HttpSession  session) {
        Customers foundCustomer = customerServices.findByCustomerEmail(customers.getCustomerEmail());
        Map<String, String> response = new HashMap<>();

        if (foundCustomer == null) {
            response.put("message", "Customer Not Found");
            return ResponseEntity.badRequest().body(response);
        }

        if (foundCustomer.getCustomerPassword().equals(customers.getCustomerPassword())) {
            session.setAttribute("customers", foundCustomer);

            response.put("message", "SignIn Successfully");
            response.put("role", foundCustomer.getRole());
            response.put("email", foundCustomer.getCustomerEmail());
            response.put("id", String.valueOf(foundCustomer.getCustomerId()));
            return ResponseEntity.ok(response);

        }else {
            response.put("message", "Incorrect Email Or Password");
            return ResponseEntity.badRequest().body(response);
        }

    }

    @PostMapping("/register")
    public ResponseEntity<Map<String, String>> register(@RequestBody Customers newCustomer) {
        Customers existCustomer = customerServices.findByCustomerEmail(newCustomer.getCustomerEmail());
        Map<String, String> response = new HashMap<>();
        if (existCustomer != null) {
            response.put("message", "Email already existed");
            return ResponseEntity.badRequest().body(response);
        }
        newCustomer.setCustomerStatus("Active");
        customerServices.saveCustomer(newCustomer);
        response.put("message", "Registration succeeded");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Customers>> getCustomers() {
        try {
            return ResponseEntity.ok(customerServices.getAllCustomers());
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{customerId}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable int customerId) {
        try {
            customerServices.deleteCustomer(customerId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }
}
