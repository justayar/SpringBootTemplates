package com.justayar.springboot.batch.tasklet;

import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.service.CovidApiBatchService;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TaskletLineReader implements Tasklet, StepExecutionListener {

    @Autowired
    CovidApiBatchService covidApiBatchService;

    private List<CovidCountryDataDTO> covidCountryDataList;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        covidCountryDataList = new ArrayList<>();
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) {

        covidCountryDataList = covidApiBatchService.getCovidSummaryData().getCountries();

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("covidDataList", this.covidCountryDataList);
        log.info("Lines Reader ended.");
        return ExitStatus.COMPLETED;
    }
}
