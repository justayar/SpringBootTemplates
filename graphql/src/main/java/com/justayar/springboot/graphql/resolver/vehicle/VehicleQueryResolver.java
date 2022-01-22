package com.justayar.springboot.graphql.resolver.vehicle;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.justayar.springboot.graphql.persistence.vehicle.Vehicle;
import com.justayar.springboot.graphql.service.VehicleService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehicleQueryResolver implements GraphQLQueryResolver {

    private final VehicleService vehicleService;

    public Vehicle getVehicleById(UUID id) {
        return vehicleService.getVehicleById(id);
    }

    public Vehicle getVehicleByName(String name) {
        return vehicleService.getVehicleByName(name);
    }

    public List<Vehicle> getVehiclesByBrand(UUID brandId) {
        return vehicleService.getVehiclesByBrand(brandId);
    }

    public List<Vehicle> getAllVehicles(int skip, int take) {
        return vehicleService.getAllVehicles(skip, take);
    }

}
