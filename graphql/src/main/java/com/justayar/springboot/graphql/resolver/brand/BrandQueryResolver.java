package com.justayar.springboot.graphql.resolver.brand;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.justayar.springboot.graphql.persistence.brand.Brand;
import com.justayar.springboot.graphql.service.BrandService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class BrandQueryResolver implements GraphQLQueryResolver {

    private final BrandService brandService;

    public Brand getBrandById(UUID id) {
        return brandService.getBrandById(id);
    }

    public Brand getBrandByName(String name) {
        return brandService.getBrandByName(name);
    }

    public List<Brand> getAllBrands(int skip, int take) {
        return brandService.getAllBrands(skip, take);
    }
}

