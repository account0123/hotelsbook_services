package com.hotelsbook.hotel.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelsbook.hotel.entity.City;
import com.hotelsbook.hotel.repository.CityRepository;

@Service
public class CityService {

    @Autowired
    private CityRepository repository;

    public int getCityIdFrom(String name) {
        return repository.findByName(name).map(City::getId).orElse(0);
    }
    
}
