package com.hotelsbook.hotel.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class HotelAvailable {
    
    @Id
    private Long id;
    private String name;
    private double price;
    private String description;
    private String picture;
    private String street;
    private String number;
    private String cityName;
    private Date availableAt;

    public HotelAvailable(String name, double price, String description, String picture, String street, String number, String cityName, Date availableAt) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
        this.street = street;
        this.number = number;
        this.cityName = cityName;
        this.availableAt = availableAt;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getPicture() {
        return picture;
    }

    public String getStreet() {
        return street;
    }

    public String getNumber() {
        return number;
    }

    public Date getAvailableAt() {
        return availableAt;
    }

    public String getCityName() {
        return cityName;
    }
}
