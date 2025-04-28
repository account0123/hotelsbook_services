package com.hotelsbook.hotel.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.RestTemplate;

import com.hotelsbook.hotel.dto.HotelServicesDTO;

public class HotelServiceClient {
    private final RestTemplate restTemplate;

    @Value("${features.services.url}")
    private String servicesUrl;

    public HotelServiceClient(RestTemplateBuilder builder) {
        this.restTemplate = builder.build();
    }

    /**
     * @throws RestClientException
     */
    public List<HotelServicesDTO> getServicesForHotels(List<Long> hotelIds) {
        final var urlParams = hotelIds.stream().map(String::valueOf).collect(Collectors.joining(","));
        final var requestUrl = String.format("%s/%s", servicesUrl, urlParams);

        final var response = restTemplate.exchange(requestUrl,
            HttpMethod.GET, 
            null, // no enviamos un body
            new ParameterizedTypeReference<List<HotelServicesDTO>>() {} // se espera un body de tipo List<HotelServiceDTO>
        );
        return response.getBody();
    }
}
