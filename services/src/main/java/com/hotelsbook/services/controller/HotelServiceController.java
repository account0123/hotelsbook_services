package com.hotelsbook.services.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hotelsbook.services.model.ErrorResponse;
import com.hotelsbook.services.service.HotelService;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/hotels")
public class HotelServiceController {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HotelService.class);
    @Autowired
    private HotelService service;

    @GetMapping("/services/{hotelIds}")
    public ResponseEntity<?> getServicesFromHotels(@PathVariable("hotelIds") String hotelIds) {
        logger.info(hotelIds);
        try {
            if (!validateHotelIds(hotelIds)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Hotel Ids must be a comma separated list of numbers"));
            }
            final var response = service.getServicesByHotelIdList(hotelIds);
            if (response.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No services found for any of the hotels [%s]".formatted(hotelIds));
            }
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            logger.error("Hotel Service Error", e);
            return ResponseEntity.internalServerError().body(new ErrorResponse(500, "Internal Server Error"));
        }
    }
    
    private boolean validateHotelIds(String hotelIds) {
        return hotelIds.matches("[0-9,]+");
    }

    @GetMapping("/services")
    public ResponseEntity<?> getServices() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Not implemented"));
    }
    
    
}
