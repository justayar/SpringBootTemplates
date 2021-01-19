package com.justayar.springboot.batch.listener;

import com.justayar.springboot.dto.CovidCountryDataDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemReadListener;

@Log4j2
public class CovidDataItemReaderListener implements ItemReadListener<CovidCountryDataDTO> {


    @Override
    public void beforeRead() {
    }

    @Override
    public void afterRead(CovidCountryDataDTO covidCountryDataDTO) {
    }

    @Override
    public void onReadError(Exception e) {
    }
}
