package com.hotelBooking.Hotel.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table (name = "packages")

public class Package {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column (name = "package_id")
    private Long id;

    @Column (name = "package_type")
    private String packageType;

    @Column (name = "package_price")
    private int packagePrice;

    @OneToMany (mappedBy = "packages", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Bookings> bookings;

    public Long getPackageId(){
        return id;
    }

    public void setPackageId (Long packageId) {
        this.id = packageId;
    }

    public String getPackageType(){
        return packageType;
    }

    public void setPackageType (String packageType) {
        this.packageType = packageType;
    }

    public int getPackagePrice() {
        return packagePrice;
    }

    public void setPackagePrice(int packagePrice) {
        this.packagePrice = packagePrice;
    }

    public List<Bookings> getBookings() {
        return bookings;
    }

    public void setBookings(List<Bookings> bookings) {
        this.bookings = bookings;
    }




}
