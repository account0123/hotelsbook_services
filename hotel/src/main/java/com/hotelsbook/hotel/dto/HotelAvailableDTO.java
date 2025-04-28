package com.hotelsbook.hotel.dto;

import java.util.List;

import com.hotelsbook.hotel.entity.HotelAvailable;

public class HotelAvailableDTO {

    private final Long id;
    private final String name;
    private double price;
    private String description;
    private String picture;
    private String street;
    private String number;
    private String cityName;
    private List<ServiceDTO> services;
    private double averageCalification;

    public HotelAvailableDTO(HotelAvailable hotel, List<ServiceDTO> services, double averageCalification) {
        this(hotel);
        this.services = services;
        this.averageCalification = averageCalification;
    }
    
    public HotelAvailableDTO(HotelAvailable hotel) {
        this.id = hotel.getId();
        this.name = hotel.getName();
        this.price = hotel.getPrice();
        this.description = hotel.getDescription();
        this.picture = hotel.getPicture();
        this.street = hotel.getStreet();
        this.number = hotel.getNumber();
        this.cityName = hotel.getCityName();
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

    public String getCityName() {
        return cityName;
    }

    public void appendServices(List<ServiceDTO> services) {
        final var actualServices = this.services;
        if (actualServices != null) {
            actualServices.addAll(services);
        } else {
            this.services = services;
        }
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public double getAverageCalification() {
        return averageCalification;
    }

    public void setAverageCalification(double averageCalification) {
        this.averageCalification = averageCalification;
    }
}
