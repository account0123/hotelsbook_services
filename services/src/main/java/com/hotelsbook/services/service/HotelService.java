package com.hotelsbook.services.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hotelsbook.services.dto.ServicesDTO;
import com.hotelsbook.services.model.ServiceResponse;
import com.hotelsbook.services.repository.HotelServiceRepository;

@Service
public class HotelService {

    private static final org.slf4j.Logger logger = org.slf4j.LoggerFactory.getLogger(HotelService.class);
    @Autowired
    private HotelServiceRepository repository;

    public List<ServicesDTO> getServicesByHotelIdList(String hotelIds) {

        logger.info("Input: {}", hotelIds);
        final var results = repository.getServicesByHotelIdList(hotelIds);
        final var hotelServicesMap = new LinkedHashMap<Long, ServicesDTO>();
        for (final Object[] row : results) {
            final var hotelId = ((Number) row[0]).longValue();
            final var hotelName = (String) row[1];
            final var hotelServices = hotelServicesMap.getOrDefault(hotelId, 
                new ServicesDTO(hotelId, hotelName, new ArrayList<>()));
            final var serviceId = ((Number) row[2]).longValue();
            final var serviceName = (String) row[3];
            hotelServices.addService(new ServiceResponse(serviceId, serviceName));
            logger.info(hotelServices.toString());
            hotelServicesMap.put(hotelId, hotelServices);
        }

        return new ArrayList<>(hotelServicesMap.values());
    }
}
