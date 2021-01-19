package com.justayar.springboot.batch.listener;

import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.persistence.entity.CovidCountryData;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemProcessListener;

@Log4j2
public class CovidDataItemProcessListener implements ItemProcessListener<CovidCountryDataDTO, CovidCountryData> {


    @Override
    public void beforeProcess(CovidCountryDataDTO covidCountryDataDTO) {
    }

    @Override
    public void afterProcess(CovidCountryDataDTO covidCountryDataDTO, CovidCountryData covidCountryData) {
    }

    @Override
    public void onProcessError(CovidCountryDataDTO covidCountryDataDTO, Exception e) {
    }
}
