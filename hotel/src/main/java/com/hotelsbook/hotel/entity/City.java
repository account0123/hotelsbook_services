package com.hotelsbook.hotel.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class City {
    @Id
    private Integer id;
    private String name;

    public Integer getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
}
