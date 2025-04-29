package com.hotelsbook.hotel.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;

import com.hotelsbook.hotel.dto.HotelAvailableDTO;
import com.hotelsbook.hotel.dto.HotelDTO;
import com.hotelsbook.hotel.dto.HotelReviewDTO;
import com.hotelsbook.hotel.dto.HotelServicesDTO;
import com.hotelsbook.hotel.entity.Hotel;
import com.hotelsbook.hotel.entity.HotelAvailable;
import com.hotelsbook.hotel.repository.HotelRepository;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelServiceClient serviceClient;

    @Autowired
    private HotelReviewClient reviewClient;

    @Value("${features.files.url}")
    private String filesUrl;

    private static final Logger logger = LoggerFactory.getLogger(HotelService.class);

    // public List<HotelAvailableDTO> getAvailableHotelsWithServicesAndReviews(Date,
    // Date, Integer) is too long
    public List<HotelAvailableDTO> getAvailableHotels(Date startDate, Date endDate, Integer cityId,
            boolean withServices, boolean withReviews) {
        final var hotels = repository.findAvailableHotelByCity(startDate, endDate, cityId);
        if (hotels.isEmpty()) {
            return List.of();
        }
        final var hotelIds = hotels.stream().map(HotelAvailable::getId).toList();
        List<HotelServicesDTO> services = List.of();
        List<HotelReviewDTO> reviews = List.of();

        // Maneja errores de conexión, para prevenir que se devuelva un error 500
        try {
            if (withServices)
                services = serviceClient.getServicesForHotels(hotelIds);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                logger.error("Connection to services microservice failed", e);
            } else {
                logger.error("Unknown exception getting services for hotels", e);
            }
        }

        try {
            if (withReviews)
                reviews = reviewClient.getReviewsForHotels(hotelIds);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                logger.error("Connection to reviews microservice failed", e);
            } else {
                logger.error("Unknown exception getting reviews for hotels", e);
            }
        }

        final var servicesByHotel = services.stream()
                .collect(Collectors.toMap(HotelServicesDTO::getHotelId, HotelServicesDTO::getServices));
        final var reviewsByHotel = reviews.stream()
                .collect(Collectors.toMap(HotelReviewDTO::getHotelId, HotelReviewDTO::getAverageCalification));

        return hotels.stream().map(hotel -> {
            final var dto = new HotelAvailableDTO(hotel);
            dto.setPicture(String.format("%s/%s", filesUrl, hotel.getPicture()));
            dto.appendServices(servicesByHotel.getOrDefault(hotel.getId(), List.of()));
            // Nullable
            final Double calification = reviewsByHotel.get(hotel.getId());
            dto.setAverageCalification(calification);
            return dto;
        }).toList();
    }

    public List<HotelDTO> getHotels(Date startDate, Date endDate, Integer cityId, boolean withServices,
            boolean withReviews) {
        final var hotels = repository.findHotelsByCity(startDate, endDate, cityId);
        final var hotelIds = hotels.stream().map(Hotel::getId).toList();

        List<HotelServicesDTO> services = List.of();
        List<HotelReviewDTO> reviews = List.of();

        // Maneja errores de conexión, para prevenir que se devuelva un error 500
        try {
            if (withServices)
                services = serviceClient.getServicesForHotels(hotelIds);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                logger.error("Connection to services microservice failed", e);
            } else {
                logger.error("Unknown exception getting services for hotels", e);
            }
        }

        try {
            if (withReviews)
                reviews = reviewClient.getReviewsForHotels(hotelIds);
        } catch (Exception e) {
            if (e instanceof RestClientException) {
                logger.error("Connection to reviews microservice failed", e);
            } else {
                logger.error("Unknown exception getting reviews for hotels", e);
            }
        }

        final var servicesByHotel = services.stream()
                .collect(Collectors.toMap(HotelServicesDTO::getHotelId, HotelServicesDTO::getServices));
        final var reviewsByHotel = reviews.stream()
                .collect(Collectors.toMap(HotelReviewDTO::getHotelId, HotelReviewDTO::getAverageCalification));

        return hotels.stream().map(hotel -> {
            final var dto = new HotelDTO(hotel);
            dto.setPicture(String.format("%s/%s", filesUrl, hotel.getPicture()));
            dto.appendServices(servicesByHotel.getOrDefault(hotel.getId(), List.of()));
            // Nullable
            final Double calification = reviewsByHotel.get(hotel.getId());
            dto.setAverageCalification(calification);
            return dto;
        }).toList();
    }
}
