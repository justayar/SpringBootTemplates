package com.justayar.springboot.batch.listener;

import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;

@Log4j2
public class CovidDataJobItemListener extends JobExecutionListenerSupport {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        log.info("Job started for user: {}.",jobExecution.getJobParameters().getString("user"));
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        log.info("Job ended for user: {}.",jobExecution.getJobParameters().getString("user"));
    }
}
