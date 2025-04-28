package com.hotelsbook.hotel.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.hotelsbook.hotel.entity.Hotel;
import com.hotelsbook.hotel.entity.HotelAvailable;

@Repository
public interface HotelRepository extends JpaRepository<HotelAvailable, Long> {
    @Query(value="call GetAvailableHotelsByCity(:startDate, :endDate, :cityId)", nativeQuery = true)
    List<HotelAvailable> findAvailableHotelByCity(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("cityId") int city
    );

    @Query(value="call GetHotelsByCity(:startDate, :endDate, :cityId)", nativeQuery = true)
    List<Hotel> findHotelsByCity(
        @Param("startDate") Date startDate,
        @Param("endDate") Date endDate,
        @Param("cityId") int city
    );
}
