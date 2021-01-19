package com.justayar.springboot.persistence.repository;

import com.justayar.springboot.persistence.entity.CovidCountryData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface CovidDataRepository extends JpaRepository<CovidCountryData,Long> {
}
