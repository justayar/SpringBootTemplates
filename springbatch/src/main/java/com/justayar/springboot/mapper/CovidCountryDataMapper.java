package com.justayar.springboot.mapper;

import com.justayar.springboot.config.MapperConfiguration;
import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.persistence.entity.CovidCountryData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.sql.Timestamp;
import java.time.OffsetDateTime;


@Mapper(config = MapperConfiguration.class)
public interface CovidCountryDataMapper {

    @Mapping(source= "date", target="transactionDate", qualifiedByName="getCurrentTime")
    CovidCountryData map(CovidCountryDataDTO covidCountryDataDTO);

    @Named("getCurrentTime")
    default OffsetDateTime getNow(Timestamp date){
       return OffsetDateTime.now();
    }
}
