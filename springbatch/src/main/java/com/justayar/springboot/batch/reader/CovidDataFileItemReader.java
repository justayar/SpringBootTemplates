package com.justayar.springboot.batch.reader;

import com.justayar.springboot.config.ApplicationProperties;
import com.justayar.springboot.dto.CovidCountryDataDTO;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.core.io.PathResource;

public class CovidDataFileItemReader extends FlatFileItemReader<CovidCountryDataDTO> {


    private static final String[] dataColumns = new String[]{"country", "countryCode", "newConfirmed", "totalConfirmed", "newDeaths", "totalDeaths", "newRecovered", "totalRecovered"};

    private static final int LINES_TO_SKIP = 1;


    public CovidDataFileItemReader(ApplicationProperties applicationProperties){

        super();

        DefaultLineMapper<CovidCountryDataDTO> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer delimitedLineTokenizer = new DelimitedLineTokenizer();
        delimitedLineTokenizer.setDelimiter(";");

        delimitedLineTokenizer.setNames(dataColumns);

        BeanWrapperFieldSetMapper<CovidCountryDataDTO> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CovidCountryDataDTO.class);

        lineMapper.setLineTokenizer(delimitedLineTokenizer);

        lineMapper.setFieldSetMapper(fieldSetMapper);

        this.setResource(new PathResource(applicationProperties.getCsvFilePath()));
        this.setLineMapper(lineMapper);
        this.setLinesToSkip(LINES_TO_SKIP);
    }
}
