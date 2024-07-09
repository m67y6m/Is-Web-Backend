package com.hotelBooking.Hotel.Controller;

import com.hotelBooking.Hotel.Models.Admin;
import com.hotelBooking.Hotel.Services.AdminServices;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/authentication")
public class AdminController {

    @Autowired
    private AdminServices adminServices;

    @PostMapping("/signIn")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Admin admin, HttpSession session) {
        Admin foundAdmin = adminServices.findByUsername(admin.getUsername());
        Map<String, String> response = new HashMap<>();

        if(foundAdmin == null) {
            response.put("message", "Admin Not Found");
            return ResponseEntity.badRequest().body(response);
        }

        if(foundAdmin.getPassword().equals(admin.getPassword())) {
            session.setAttribute("admin", foundAdmin);

            Admin adminRole = foundAdmin;
            Admin userName = foundAdmin;

            response.put("message", "SignIn Successfull");
            response.put("role", adminRole.getRole());
            response.put("username", userName.getUsername());

            return ResponseEntity.ok(response);

        }else {
            response.put("message", "Incorrect UserName Or Password");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpSession session) {
        session.invalidate();
        return ResponseEntity.ok("Logout Sucessfull");
    }

    @GetMapping("/checkSession")
    public ResponseEntity<Map<String, String>> checkSession(HttpSession session) {
        Map<String, String> response = new HashMap<>();
        if(session.getAttribute("user") != null) {
            response.put("message", "User Is Logged In");
            return ResponseEntity.ok(response);
        }else {
            response.put("message", "user Not Logged In");
            return ResponseEntity.status(401).body(response);
        }
    }
}

