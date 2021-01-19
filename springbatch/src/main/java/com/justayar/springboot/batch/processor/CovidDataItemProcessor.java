package com.justayar.springboot.batch.processor;

import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.mapper.CovidCountryDataMapper;
import com.justayar.springboot.persistence.entity.CovidCountryData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

@Slf4j
public class CovidDataItemProcessor implements ItemProcessor<CovidCountryDataDTO, CovidCountryData> {

    @Autowired
    private CovidCountryDataMapper covidCountryDataMapper;

    @Override
    public CovidCountryData process(CovidCountryDataDTO covidCountryDataDTO){
        if(covidCountryDataDTO.getCountryCode().equalsIgnoreCase("TR")){
            covidCountryDataDTO.setNewConfirmed(covidCountryDataDTO.getNewConfirmed()/10);
            covidCountryDataDTO.setTotalConfirmed(covidCountryDataDTO.getTotalConfirmed()/10);
        }

        return covidCountryDataMapper.map(covidCountryDataDTO);
    }
}
