package com.hotelsbook.hotel.dto;

public class HotelReviewDTO {
    private long hotelId;
    private double averageCalification;
    
    public HotelReviewDTO(long hotelId, double calification) {
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
