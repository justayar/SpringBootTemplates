package com.justayar.springboot.batch.tasklet;

import com.justayar.springboot.persistence.entity.CovidCountryData;
import com.justayar.springboot.persistence.repository.CovidDataRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Log4j2
public class TaskletLineWriter implements Tasklet, StepExecutionListener {

    @Autowired
    private CovidDataRepository covidDataRepository;

    private List<CovidCountryData> countryDataList;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();

        this.countryDataList = (List<CovidCountryData>) executionContext.get("processedCovidDataList");
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext){

        if(countryDataList != null) {
            covidDataRepository.saveAll(countryDataList);
        }
        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("Lines Writer ended.");
        return ExitStatus.COMPLETED;
    }

}
