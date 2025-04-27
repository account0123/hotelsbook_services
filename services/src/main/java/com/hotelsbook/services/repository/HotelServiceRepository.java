package com.hotelsbook.services.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelsbook.services.entity.ServiceEntity;

@Repository
public interface HotelServiceRepository extends JpaRepository<ServiceEntity, Long> {
    @Query(value = "call GetServicesByHotels(:hotelIds)", nativeQuery = true)
    List<Object[]> getServicesByHotelIdList(@Param("hotelIds") String hotelIds);
}
