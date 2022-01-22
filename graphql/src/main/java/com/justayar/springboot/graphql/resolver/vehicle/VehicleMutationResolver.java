package com.justayar.springboot.graphql.resolver.vehicle;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.justayar.springboot.graphql.dto.VehicleInput;
import com.justayar.springboot.graphql.persistence.vehicle.Vehicle;
import com.justayar.springboot.graphql.service.VehicleService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class VehicleMutationResolver implements GraphQLMutationResolver {

    private final VehicleService vehicleService;

    public Vehicle addNewVehicle(VehicleInput vehicleInput) {
        return vehicleService.addNewVehicle(vehicleInput);
    }
}
