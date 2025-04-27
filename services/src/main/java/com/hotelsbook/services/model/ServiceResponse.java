package com.hotelsbook.services.model;

public class ServiceResponse {
    private Long id;
    private String name;

    public ServiceResponse(Long serviceId, String serviceName) {
        this.id = serviceId;
        this.name = serviceName;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "ServiceResponse [id=" + id + ", name=" + name + "]";
    }
}
