package com.justayar.springboot.controller;

import com.justayar.springboot.service.CovidApiBatchService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;


@RestController
@RequiredArgsConstructor
@RequestMapping(value="/conversion")
public class CovidBatchController {

    @Autowired
    private CovidApiBatchService covidApiBatchService;

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job batchJob;

    @Autowired
    private Job taskletJob;

    @GetMapping(value="/startBatch",consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value="Get current covid data with batch", notes="Get current covid data with batch and write it to db.")
    public ResponseEntity<String> startBatch(String user) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("user", user)
                .addDate("startDate",new Date())
                .toJobParameters();

        jobLauncher.run(batchJob, jobParameters);
        return ResponseEntity.ok("OK");
    }

    @GetMapping(value="/startTasklet",consumes = {MediaType.ALL_VALUE})
    @ApiOperation(value="Get current covid data with tasklet", notes="Get current covid data with tasklet and write it to db.")
    public ResponseEntity<String> startTasklet(String user) throws JobParametersInvalidException, JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException {
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("user", user)
                .addDate("startDate",new Date())
                .toJobParameters();

        jobLauncher.run(taskletJob, jobParameters);
        return ResponseEntity.ok("OK");
    }
}
