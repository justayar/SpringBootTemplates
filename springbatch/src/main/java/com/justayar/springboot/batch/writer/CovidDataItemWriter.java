package com.justayar.springboot.batch.writer;

import com.justayar.springboot.persistence.entity.CovidCountryData;
import com.justayar.springboot.persistence.repository.CovidDataRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CovidDataItemWriter implements ItemWriter<CovidCountryData> {

    @Autowired
    private CovidDataRepository covidDataRepository;

    @Override
    public void write(List<? extends CovidCountryData> countryDataList){
        if(countryDataList != null) {
            covidDataRepository.saveAll(countryDataList);
        }
    }
}
