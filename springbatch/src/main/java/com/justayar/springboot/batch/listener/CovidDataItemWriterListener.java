package com.justayar.springboot.batch.listener;

import com.justayar.springboot.persistence.entity.CovidCountryData;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ItemWriteListener;

import java.util.List;

@Log4j2
public class CovidDataItemWriterListener implements ItemWriteListener<CovidCountryData> {


    @Override
    public void beforeWrite(List<? extends CovidCountryData> list) {
    }

    @Override
    public void afterWrite(List<? extends CovidCountryData> list) {
    }

    @Override
    public void onWriteError(Exception e, List<? extends CovidCountryData> list) {
    }
}
