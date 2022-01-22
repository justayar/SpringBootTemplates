package com.justayar.springboot.graphql.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.justayar.springboot.graphql.dto.VehicleInput;
import com.justayar.springboot.graphql.exception.BrandNotFoundException;
import com.justayar.springboot.graphql.exception.VehicleNotFoundException;
import com.justayar.springboot.graphql.persistence.brand.Brand;
import com.justayar.springboot.graphql.persistence.vehicle.Vehicle;
import com.justayar.springboot.graphql.persistence.vehicle.VehicleRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class VehicleService {

    private final VehicleRepository vehicleRepository;
    private final BrandService brandService;

    public Vehicle getVehicleById(UUID id) {
        log.info("Vehicle queried with id {}", id);
        return vehicleRepository.findById(id)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with id " + id));
    }

    public Vehicle getVehicleByName(String name) {
        log.info("Vehicle queried with name {}", name);
        return vehicleRepository.getVehicleByName(name)
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found with name " + name));
    }

    public Vehicle addNewVehicle(VehicleInput vehicleInput) {
        log.info("Adding new vehicle with name {}", vehicleInput.getName());
        Brand brand = brandService.getBrandById(vehicleInput.getBrandId());
        return vehicleRepository.save(mapInputToVehicle(vehicleInput, brand));
    }

    public List<Vehicle> getVehiclesByBrand(UUID brandId) {
        Brand brand = brandService.getBrandById(brandId);
        log.info("Getting vehicles with brand {}", brand.getName());
        return vehicleRepository.getVehiclesByBrandId(brandId)
                .orElseThrow(() -> new BrandNotFoundException("No vehicle found with brand name "+ brand.getName()));
    }

    public List<Vehicle> getAllVehicles(int skip, int take) {
        log.info("Getting first {} vehicles by skipping first {} vehicles", take, skip);
        return vehicleRepository.getVehicles(skip, take)
                .orElseThrow(() -> new VehicleNotFoundException("No vehicle found"));
    }


    private Vehicle mapInputToVehicle(VehicleInput vehicleInput, Brand brand) {
        Vehicle vehicle = new Vehicle();
        vehicle.setName(vehicleInput.getName());
        vehicle.setHorsePower(vehicleInput.getHorsePower());
        vehicle.setBodyMass(vehicleInput.getBodyMass());
        vehicle.setFuelType(vehicleInput.getFuelType());
        vehicle.setBrand(brand);
        return vehicle;
    }

}
