package com.justayar.springboot.graphql.persistence.vehicle;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface VehicleRepository extends JpaRepository<Vehicle, UUID> {

    Optional<Vehicle> getVehicleByName(String name);

    Optional<List<Vehicle>> getVehiclesByBrandId(UUID brandId);

    @Query(value = "SELECT * FROM vehicle ORDER BY created_at OFFSET ?1*?2 LIMIT ?2", nativeQuery = true)
    Optional<List<Vehicle>> getVehicles(Integer skip, Integer take);
}
