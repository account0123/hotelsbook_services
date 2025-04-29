package com.hotelsbook.hotel.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.hotelsbook.hotel.entity.City;
import com.hotelsbook.hotel.repository.CityRepository;

class ServiceUnitTests {
    
    @Mock
    private CityRepository cityRepository;

    @InjectMocks
    private CityService cityService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetCityIdFrom_whenCityExists() {
        String cityName = "Santiago";
        City city = new City();
        city.setId(1);
        when(cityRepository.findByName(cityName)).thenReturn(Optional.of(city));

        int result = cityService.getCityIdFrom(cityName);

        assertEquals(1, result, "This city should exist (should return ID 1)");
        verify(cityRepository, times(1)).findByName(cityName);
    }

    @Test
    void testGetCityIdFrom_whenCityDoesNotExist() {
        String cityName = "Talca";
        when(cityRepository.findByName(cityName)).thenReturn(Optional.empty());

        int result = cityService.getCityIdFrom(cityName);

        assertEquals(0, result, "This city should not exist (should return 0)");
        verify(cityRepository, times(1)).findByName(cityName);
    }
}
