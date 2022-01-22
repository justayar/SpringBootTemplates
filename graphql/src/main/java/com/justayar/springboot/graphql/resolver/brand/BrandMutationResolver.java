package com.justayar.springboot.graphql.resolver.brand;

import org.springframework.stereotype.Component;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.justayar.springboot.graphql.dto.BrandInput;
import com.justayar.springboot.graphql.persistence.brand.Brand;
import com.justayar.springboot.graphql.service.BrandService;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BrandMutationResolver implements GraphQLMutationResolver {

    private final BrandService brandService;

    public Brand addNewBrand(BrandInput brandInput) {
        return brandService.addNewBrand(brandInput);
    }
}
