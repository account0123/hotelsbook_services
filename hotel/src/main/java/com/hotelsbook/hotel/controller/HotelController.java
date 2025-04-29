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
import com.hotelsbook.hotel.service.CityService;
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

    @Autowired
    private CityService cityService;

    @GetMapping("/available")
    public ResponseEntity<?> getAvailableHotels(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean withServices,
            @RequestParam(required = false) Boolean withReviews) {
        logger.info("startDate {}, endDate {}, cityId {}", startDate, endDate, city);
        logger.info("withServices {}, withreviews {}", withServices, withReviews);
        try {
            // null values are parsed as true (present)
            withServices = withServices == null ? true : withServices;
            withReviews = withReviews == null ? true : withReviews;
            final int cityId = cityService.getCityIdFrom(city);
            if (cityId == 0) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "City '%s' not found".formatted(city)),
                        HttpStatus.NOT_FOUND);
            }
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
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate,
            @RequestParam(required = false) String city,
            @RequestParam(required = false) Boolean withServices,
            @RequestParam(required = false) Boolean withReviews) {
        logger.info("startDate {}, endDate {}, cityId {}", startDate, endDate, city);
        logger.info("withServices {}, withreviews {}", withServices, withReviews);
        try {
            // null values are parsed as true (present)
            withServices = withServices == null ? true : withServices;
            withReviews = withReviews == null ? true : withReviews;
            final int cityId = cityService.getCityIdFrom(city);
            if (cityId == 0) {
                return new ResponseEntity<>(new ErrorResponse(HttpStatus.NOT_FOUND, "City '%s' not found".formatted(city)),
                        HttpStatus.NOT_FOUND);
            }
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
