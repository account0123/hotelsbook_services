package com.hotelsbook.hotel.dto;

import java.util.List;

public class HotelServicesDTO {
    private Long hotelId;
    private String hotelName;
    private List<ServiceDTO> services;

    public HotelServicesDTO(Long hotelId, String hotelName, List<ServiceDTO> services) {
        this.hotelId = hotelId;
        this.hotelName = hotelName;
        this.services = services;
    }

    public Long getHotelId() {
        return hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public List<ServiceDTO> getServices() {
        return services;
    }

    public int addService(ServiceDTO service) {
        services.add(service);
        return services.size();
    }

    public boolean removeServiceById(Long serviceId) {
        return services.removeIf(service -> service.getId().equals(serviceId));
    }

    @Override
    public String toString() {
        return "HotelServicesDTO [hotelId=" + hotelId + ", hotelName=" + hotelName + ", services=" + services + "]";
    }
}
