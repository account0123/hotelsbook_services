package com.hotelsbook.hotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.hotelsbook.hotel.entity.City;
import com.hotelsbook.hotel.repository.CityRepository;

@SpringBootTest
@ActiveProfiles("test")
public class ServiceIntegrationTests {
    
    @Autowired
    private CityService cityService;

    @Autowired
    private CityRepository cityRepository;

    @Test
    void testGetCityIdFrom_whenCityExists() {

        // Save city
        final var city = new City();
        city.setName("Santiago");
        city.setId(1);
        cityRepository.save(city);

        final var foundCity = cityRepository.findByName("Santiago");
        assertTrue(foundCity.isPresent());

        final var result = cityService.getCityIdFrom("Santiago");
        assertEquals(1, result);
    }

    @Test
    void testGetCityIdFrom_whenCityDoesNotExist() {

        // No save

        final var foundCity = cityRepository.findByName("Talca");
        assertTrue(foundCity.isEmpty());

        final var result = cityService.getCityIdFrom("Talca");
        assertEquals(0, result);
    }
}
