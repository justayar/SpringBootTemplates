package com.justayar.springboot.graphql.persistence.brand;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BrandRepository extends JpaRepository<Brand, UUID> {

    Optional<Brand> getBrandByName(String name);

    @Query(value = "SELECT * FROM brand ORDER BY created_at OFFSET ?1*?2 LIMIT ?2", nativeQuery = true)
    Optional<List<Brand>> getBrands(Integer skip, Integer take);
}
