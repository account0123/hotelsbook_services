package com.hotelsbook.reviews.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ReviewEntity {
    
    @Id
    private Long hotelId;
    private String hotelName;
    private double averageCalification;

    ReviewEntity(Long hotelId, String hotelName, double calification) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.averageCalification = calification;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public double getAverageCalification() {
        return averageCalification;
    }
}
