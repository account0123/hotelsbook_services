package com.hotelsbook.hotel.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
public class Hotel {
    
    @Id
    private Long id;
    private String name;
    private double price;
    private String description;
    private String picture;
    private String street;
    private String number;
    private String cityName;
     
    @Temporal(TemporalType.DATE)
    private Date available;

    Hotel() {}

    public Hotel(String name, double price, String description, String picture, String street, String number, String cityName, Date available) {
        this.name = name;
        this.price = price;
        this.description = description;
        this.picture = picture;
        this.street = street;
        this.number = number;
        this.cityName = cityName;
        this.available = available;
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

    public Date getAvailable() {
        return available;
    }

    public String getCityName() {
        return cityName;
    }
}
