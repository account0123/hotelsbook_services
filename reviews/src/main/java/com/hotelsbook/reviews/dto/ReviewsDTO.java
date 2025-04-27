package com.hotelsbook.reviews.dto;

public class ReviewsDTO {
    private long hotelId;
    private double averageCalification;
    
    public ReviewsDTO(long hotelId, double calification) {
        this.hotelId = hotelId;
        this.averageCalification = calification;
    }

    public long getHotelId() {
        return hotelId;
    }

    public double getAverageCalification() {
        return averageCalification;
    }
}
