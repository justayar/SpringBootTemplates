package com.justayar.springboot.batch.reader;

import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.service.CovidApiBatchService;
import org.springframework.batch.item.ItemReader;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CovidDataItemReader implements ItemReader<CovidCountryDataDTO> , InitializingBean {

    @Autowired
    CovidApiBatchService covidApiBatchService;

    private List<CovidCountryDataDTO> covidCountryDataList;
    private Integer index = 0;
    private boolean initialized;

    private void init() {

        covidCountryDataList = covidApiBatchService.getCovidSummaryData().getCountries();
        initialized = true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        init();
    }


    @Override
    public CovidCountryDataDTO read(){

        if(!initialized)
            init();

        if(index == covidCountryDataList.size()){
            index = 0;
            initialized = false;
            return null;
        }

        CovidCountryDataDTO countryDTO = covidCountryDataList.get(index);
        index++;
        return countryDTO;
    }
}
