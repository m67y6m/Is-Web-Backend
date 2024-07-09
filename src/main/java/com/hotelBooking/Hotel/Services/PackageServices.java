package com.hotelBooking.Hotel.Services;

import com.hotelBooking.Hotel.Repository.PackageRepository;
import com.hotelBooking.Hotel.Models.Package;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PackageServices {

    @Autowired
    private PackageRepository packageRepository;

    public Package savePackage(Package packages) {
        return packageRepository.save(packages);
    }

    public List<Package> getAllpackage() {
        return packageRepository.findAll();
    }

    public void deletePackage(int packageId) {
        packageRepository.deleteById((int) packageId);
    }

    public Package updatePackage(Package packages) {
        Package existPackage = packageRepository.findById(packages.getPackageId()).get();
        existPackage.setPackageType(packages.getPackageType());
        existPackage.setPackagePrice(packages.getPackagePrice());
        Package updatePackage = packageRepository.save(existPackage);
        return updatePackage;
    }


}
