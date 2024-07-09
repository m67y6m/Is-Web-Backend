package com.hotelBooking.Hotel.Services;

import com.hotelBooking.Hotel.Models.Admin;
import org.mindrot.jbcrypt.BCrypt;
import com.hotelBooking.Hotel.Repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminServices {

    @Autowired
    private AdminRepository adminRepository;

    public Admin findByUsername(String username)
    {
        return adminRepository.findByUsername(username);
    }

    public Admin findByRole(String role)
    {
        return adminRepository.findByRole(role);
    }

    public void saveAdmin (Admin admin)
    {
        adminRepository.save(admin);
    }

    private String hashPassword(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public boolean checkPassword(String plainTextPassword, String  hashedPassword)
    {
        return BCrypt.checkpw(plainTextPassword, hashedPassword);
    }

}
