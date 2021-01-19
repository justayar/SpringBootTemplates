package com.justayar.springboot.batch.config;

import com.justayar.springboot.batch.tasklet.TaskletLineProcessor;
import com.justayar.springboot.batch.tasklet.TaskletLineReader;
import com.justayar.springboot.batch.tasklet.TaskletLineWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TaskletConfig {

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Bean
    Step lineReader(){
        return stepBuilderFactory.get("lineReaderStep")
                .tasklet(taskletLineReader())
                .build();
    }

    @Bean
    Step lineProcessor(){
        return stepBuilderFactory.get("lineProcessorStep")
                .tasklet(taskletLineProcessor())
                .build();
    }

    @Bean
    Step lineWriter(){
        return stepBuilderFactory.get("lineWriterStep")
                .tasklet(taskletLineWriter())
                .build();
    }

    @Bean
    public Job taskletJob(){
        return jobBuilderFactory
                .get("taskletJob")
                .start(lineReader())
                .next(lineProcessor())
                .next(lineWriter())
                .build();
    }

    @Bean
    TaskletLineReader taskletLineReader(){ return new TaskletLineReader(); }

    @Bean
    TaskletLineProcessor taskletLineProcessor(){ return new TaskletLineProcessor(); }

    @Bean
    TaskletLineWriter taskletLineWriter(){ return new TaskletLineWriter(); }

}
