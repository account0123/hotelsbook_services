package com.hotelsbook.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.hotelsbook.hotel.dto.HotelReviewDTO;

@Service
public class HotelReviewClient {
     private final RestTemplate restTemplate;

    @Value("${features.reviews.url}")
    private String reviewsUrl;

    public HotelReviewClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * @throws RestClientException
     */
    public List<HotelReviewDTO> getReviewsForHotels(List<Long> hotelIds) {
        final var urlParams = hotelIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        final var requestUrl = String.format("%s/%s", reviewsUrl, urlParams);

        final var response = restTemplate.exchange(requestUrl,
            HttpMethod.GET, 
        null,
            new ParameterizedTypeReference<List<HotelReviewDTO>>() {}
        );
        return response.getBody();
    }
}
