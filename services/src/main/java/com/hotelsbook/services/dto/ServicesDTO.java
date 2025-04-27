package com.hotelsbook.services.dto;
import java.util.List;

import com.hotelsbook.services.model.ServiceResponse;

public class ServicesDTO {
    private Long hotelId;
    private String hotelName;
    private List<ServiceResponse> services;

    public ServicesDTO(Long hotelId, String hotelName, List<ServiceResponse> services) {
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

    public List<ServiceResponse> getServices() {
        return services;
    }

    public int addService(ServiceResponse service) {
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
