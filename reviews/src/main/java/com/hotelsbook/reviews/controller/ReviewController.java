package com.hotelsbook.reviews.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelsbook.reviews.service.ReviewService;
import com.hotelsbook.reviews.ErrorResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/hotels")
public class ReviewController {
    private static final Logger logger = LoggerFactory.getLogger(ReviewController.class);

    @Autowired
    private ReviewService service;

    @GetMapping("/reviews/{hotelIds}")
    public ResponseEntity<?> getReviewsForHotels(@PathVariable("hotelIds") String hotelIds) {
        try {
            logger.info("Getting reviews for hotels: {}", hotelIds);
            if (!validateHotelIds(hotelIds)) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.BAD_REQUEST, "Hotel ids must be a comma separated list of numbers"), HttpStatus.BAD_REQUEST);
            }
            final var response = service.getCalificationForHotelIds(hotelIds);
            if (response.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No reviews found for hotels %s".formatted(hotelIds)), HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(response, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("Error getting reviews for hotels: {}", hotelIds, e);
            return new ResponseEntity<>(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private boolean validateHotelIds(String hotelIds) {
        return hotelIds.matches("[0-9,]+");
    }
    
}
