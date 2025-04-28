package com.hotelsbook.hotel.service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import com.hotelsbook.hotel.dto.HotelAvailableDTO;
import com.hotelsbook.hotel.dto.HotelReviewDTO;
import com.hotelsbook.hotel.dto.HotelServicesDTO;
import com.hotelsbook.hotel.entity.HotelAvailable;
import com.hotelsbook.hotel.repository.HotelRepository;

public class HotelService {

    @Autowired
    private HotelRepository repository;

    @Autowired
    private HotelServiceClient serviceClient;

    @Autowired
    private HotelReviewClient reviewClient;

    // public List<HotelAvailableDTO> getAvailableHotelsWithServicesAndReviews(Date, Date, Integer) is too long
    public List<HotelAvailableDTO> getAvailableHotels(Date startDate, Date endDate, Integer cityId, boolean withServices, boolean withReviews) {
        final var hotels = repository.findAvailableHotelByCity(startDate, endDate, cityId);
        if (hotels.isEmpty()) {
            return List.of();
        }
        final var hotelIds = hotels.stream().map(HotelAvailable::getId).toList();

        final List<HotelServicesDTO> services = withServices ? serviceClient.getServicesForHotels(hotelIds) : List.of();
        final List<HotelReviewDTO> reviews = withReviews ? reviewClient.getReviewsForHotels(hotelIds) : List.of();

        final var servicesByHotel = services.stream().collect(Collectors.toMap(HotelServicesDTO::getHotelId, HotelServicesDTO::getServices));
        final var reviewsByHotel = reviews.stream().collect(Collectors.toMap(HotelReviewDTO::getHotelId, HotelReviewDTO::getAverageCalification));

        return hotels.stream().map(hotel -> {
            final var dto = new HotelAvailableDTO(hotel);
            dto.appendServices(servicesByHotel.getOrDefault(hotel.getId(), List.of()));
            // Nullable
            dto.setAverageCalification(reviewsByHotel.get(hotel.getId()));
            return dto;
        }).toList();
    }

    public List<HotelAvailableDTO> getHotels(Date startDate, Date endDate, Integer cityId, boolean withServices, boolean withReviews) {
        final var hotels = repository.findHotelsByCity(startDate, endDate, cityId);
        final var hotelIds = hotels.stream().map(HotelAvailable::getId).toList();

        final List<HotelServicesDTO> services = withServices ? serviceClient.getServicesForHotels(hotelIds) : List.of();
        final List<HotelReviewDTO> reviews = withReviews ? reviewClient.getReviewsForHotels(hotelIds) : List.of();

        final var servicesByHotel = services.stream().collect(Collectors.toMap(HotelServicesDTO::getHotelId, HotelServicesDTO::getServices));
        final var reviewsByHotel = reviews.stream().collect(Collectors.toMap(HotelReviewDTO::getHotelId, HotelReviewDTO::getAverageCalification));

        return hotels.stream().map(hotel -> {
            final var dto = new HotelAvailableDTO(hotel);
            dto.appendServices(servicesByHotel.getOrDefault(hotel.getId(), List.of()));
            // Nullable
            dto.setAverageCalification(reviewsByHotel.get(hotel.getId()));
            return dto;
        }).toList();
    }
}
