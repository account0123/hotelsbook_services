package com.hotelsbook.hotel.controller;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hotelsbook.hotel.response.ErrorResponse;
import com.hotelsbook.hotel.service.HotelService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/hotels")
public class HotelController {

    private static final Logger logger = LoggerFactory.getLogger(HotelController.class);

    @Autowired
    private HotelService hotelService;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableHotels(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam Integer cityId,
            @RequestParam(value = "include_services", defaultValue = "false") boolean withServices,
            @RequestParam(value = "include_reviews", defaultValue = "false") boolean withReviews) {
        logger.info("startDate %s, endDate %s, cityId %s", startDate, endDate, cityId);
        logger.info("include_services %s, include_reviews %s", withServices, withReviews);
        try {
            final var availableHotels = hotelService.getAvailableHotels(startDate, endDate, cityId, withServices,
                    withReviews);
            if (availableHotels.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No available hotels found"),
                        HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(availableHotels);
        } catch (Exception e) {
            logger.error("Error getting available hotels", e);
            return ResponseEntity.internalServerError()
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
        }
    }

    @GetMapping
    public ResponseEntity<?> getHotels(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam Integer cityId,
            @RequestParam(value = "include_services", defaultValue = "false") boolean withServices,
            @RequestParam(value = "include_reviews", defaultValue = "false") boolean withReviews) {
        logger.info("startDate %s, endDate %s, cityId %s", startDate, endDate, cityId);
        logger.info("include_services %s, include_reviews %s", withServices, withReviews);
        try {
            final var hotels = hotelService.getHotels(startDate, endDate, cityId, withServices,
                    withReviews);
            if (hotels.isEmpty()) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "No hotels found"),
                        HttpStatus.NOT_FOUND);
            }
            return ResponseEntity.ok(hotels);
        } catch (Exception e) {
            logger.error("Error getting hotels", e);
            return ResponseEntity.internalServerError()
                    .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error"));
        }
    }
}
