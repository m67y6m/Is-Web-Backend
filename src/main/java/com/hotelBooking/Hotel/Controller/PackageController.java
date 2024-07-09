package com.hotelBooking.Hotel.Controller;


import com.hotelBooking.Hotel.Services.PackageServices;
import com.hotelBooking.Hotel.Models.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/package")
public class PackageController {

    @Autowired
    private PackageServices packageServices;

    @PostMapping("/add")
    public ResponseEntity<Map<String, String>> add(@RequestBody Package addPackage) {
        packageServices.savePackage(addPackage);
        Map<String, String> response = new HashMap<>();
        response.put("message", "Package Added");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/get")
    public ResponseEntity<List<Package>> getPackage() {
        try {
            return ResponseEntity.ok(packageServices.getAllpackage());
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }

    @DeleteMapping("/delete/{packageId}")
    public ResponseEntity<Void> deletePackage(@PathVariable int packageId) {
        try {
            packageServices.deletePackage(packageId);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).build();
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Package> updatePackage(@PathVariable("id") Long  packageId,@RequestBody Package packages)
    {
        packages.setPackageId(packageId);
        Package updatedPackage = packageServices.updatePackage(packages);
        return new ResponseEntity<Package>(updatedPackage, HttpStatus.CREATED);
    }
}
