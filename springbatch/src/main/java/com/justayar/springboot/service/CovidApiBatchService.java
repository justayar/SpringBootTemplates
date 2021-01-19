package com.justayar.springboot.service;

import com.justayar.springboot.dto.CovidSummaryDTO;
import org.springframework.stereotype.Service;

@Service
public interface CovidApiBatchService {

    CovidSummaryDTO getCovidSummaryData();

}
