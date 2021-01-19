package com.justayar.springboot.batch.writer;

import com.justayar.springboot.config.ApplicationProperties;
import com.justayar.springboot.persistence.entity.CovidCountryData;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.core.io.PathResource;

public class CovidDataFileItemWriter extends FlatFileItemWriter<CovidCountryData> {

    private static final String[] dataColumns = new String[]{"country", "countryCode", "newConfirmed", "totalConfirmed", "newDeaths", "totalDeaths", "newRecovered", "totalRecovered"};


    public CovidDataFileItemWriter(ApplicationProperties applicationProperties){

        super();

        this.setHeaderCallback(writer -> writer.write("Country;CountryCode;New Confirmed;Total Confirmed;New Deaths;Total Deaths;New Recovered;Total Recovered"));

        this.setAppendAllowed(true);

        DelimitedLineAggregator<CovidCountryData> delimitedLineAggregator = new DelimitedLineAggregator<>();

        delimitedLineAggregator.setDelimiter(";");

        BeanWrapperFieldExtractor<CovidCountryData> fieldExtractor = new BeanWrapperFieldExtractor<>();

        fieldExtractor.setNames(dataColumns);

        delimitedLineAggregator.setFieldExtractor(fieldExtractor);


        this.setResource(new PathResource(applicationProperties.getCsvFilePath()));
        this.setLineAggregator(delimitedLineAggregator);

    }

}
