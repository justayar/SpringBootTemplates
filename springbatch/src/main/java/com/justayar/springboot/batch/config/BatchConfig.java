package com.justayar.springboot.batch.config;

import com.justayar.springboot.batch.listener.CovidDataItemProcessListener;
import com.justayar.springboot.batch.listener.CovidDataItemReaderListener;
import com.justayar.springboot.batch.listener.CovidDataItemWriterListener;
import com.justayar.springboot.batch.listener.CovidDataJobItemListener;
import com.justayar.springboot.batch.processor.CovidDataItemProcessor;
import com.justayar.springboot.batch.reader.CovidDataFileItemReader;
import com.justayar.springboot.batch.reader.CovidDataItemReader;
import com.justayar.springboot.batch.writer.CovidDataFileItemWriter;
import com.justayar.springboot.batch.writer.CovidDataItemWriter;
import com.justayar.springboot.config.ApplicationProperties;
import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.persistence.entity.CovidCountryData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BatchConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private ApplicationProperties applicationProperties;

    @Bean
    Step saveDataFromApiToCsvFileStep(){
        return stepBuilderFactory.get("saveDataFromApiToCsvFileStep")
                .<CovidCountryDataDTO,CovidCountryData>chunk(5)
                .reader(reader())
                .processor(processor())
                .writer(fileItemWriter())
                .listener(processListener())
                .listener(readerListener())
                .listener(writerListener())
                .build();
    }

    @Bean
    Step saveDataFromCsvFileToDbStep(){
        return stepBuilderFactory.get("saveDataFromCsvFileToDbStep")
                .<CovidCountryDataDTO,CovidCountryData>chunk(5)
                .reader(fileItemReader())
                .processor(processor())
                .writer(writer())
                .listener(processListener())
                .listener(readerListener())
                .listener(writerListener())
                .build();
    }

    @Bean
    Job batchJob(){
        return jobBuilderFactory.get("job1")
                .incrementer(new RunIdIncrementer())
                .listener(listener())
                .flow(saveDataFromApiToCsvFileStep())
                .next(saveDataFromCsvFileToDbStep())
                .end()
                .build();
    }

    @Bean
    CovidDataJobItemListener listener(){ return new CovidDataJobItemListener(); }

    @Bean
    CovidDataItemProcessListener processListener(){ return new CovidDataItemProcessListener(); }

    @Bean
    CovidDataItemWriterListener writerListener(){ return new CovidDataItemWriterListener(); }

    @Bean
    CovidDataItemReaderListener readerListener(){ return new CovidDataItemReaderListener(); }

    @Bean
    CovidDataItemReader reader(){ return new CovidDataItemReader(); }

    @Bean
    CovidDataItemProcessor processor(){ return new CovidDataItemProcessor(); }

    @Bean
    CovidDataItemWriter writer(){ return new CovidDataItemWriter(); }

    @Bean
    CovidDataFileItemReader fileItemReader(){ return new CovidDataFileItemReader(applicationProperties); }

    @Bean
    CovidDataFileItemWriter fileItemWriter(){ return new CovidDataFileItemWriter(applicationProperties); }
}
