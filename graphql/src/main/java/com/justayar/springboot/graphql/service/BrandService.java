package com.justayar.springboot.graphql.service;

import java.util.List;
import java.util.UUID;

import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

import com.justayar.springboot.graphql.dto.BrandInput;
import com.justayar.springboot.graphql.exception.BrandNotFoundException;
import com.justayar.springboot.graphql.persistence.brand.Brand;
import com.justayar.springboot.graphql.persistence.brand.BrandRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@AllArgsConstructor
@Slf4j
public class BrandService {

    private final BrandRepository brandRepository;

    public Brand getBrandById(UUID id) {
        log.info("Brand queried with id {}", id);
        return brandRepository.findById(id)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found with id " + id));
    }

    public Brand getBrandByName(String name) {
        log.info("Brand queried with name {}", name);
        return brandRepository.getBrandByName(name)
                .orElseThrow(() -> new BrandNotFoundException("Brand not found with name " + name));
    }

    public List<Brand> getAllBrands(int skip, int take) {
        log.info("Getting first {} brands by skipping first {} brands", take, skip);
        return brandRepository.getBrands(skip, take)
                .orElseThrow(() -> new BrandNotFoundException("No brand found"));
    }

    public Brand addNewBrand(BrandInput brandInput) {
        log.info("Adding new brand with name {}", brandInput.getName());
        Brand brand = mapInputToBrand(brandInput);
        return brandRepository.save(brand);
    }

    @NotNull
    private Brand mapInputToBrand(BrandInput brandInput) {
        Brand brand = new Brand();
        brand.setName(brandInput.getName());
        brand.setFoundedBy(brandInput.getFoundedBy());
        return brand;
    }
}
