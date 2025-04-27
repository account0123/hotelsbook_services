package com.hotelsbook.services.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class ServiceEntity {
    @Id
    private Long hotelId;

    public ServiceEntity() {
        
    }
}
