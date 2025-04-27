package com.hotelsbook.reviews.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelsbook.reviews.dto.ReviewsDTO;
import com.hotelsbook.reviews.repository.ReviewRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository repository;
    
    public List<ReviewsDTO> getCalificationForHotelIds(String hotelIds) {
        final var results = repository.findAverageCalificationFor(hotelIds);
        return results.stream().map(
            result -> new ReviewsDTO(
                ((Number) result[0]).longValue(), // hotelId
                ((Number) result[2]).doubleValue() // averageCalification
            )).toList();
    }
}
