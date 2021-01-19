package com.justayar.springboot.batch.tasklet;

import com.justayar.springboot.dto.CovidCountryDataDTO;
import com.justayar.springboot.mapper.CovidCountryDataMapper;
import com.justayar.springboot.persistence.entity.CovidCountryData;
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

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class TaskletLineProcessor implements Tasklet, StepExecutionListener {

    @Autowired
    private CovidCountryDataMapper covidCountryDataMapper;

    private List<CovidCountryDataDTO> covidCountryDataList;

    private List<CovidCountryData> countryDataList;


    @Override
    public void beforeStep(StepExecution stepExecution) {
        ExecutionContext executionContext = stepExecution
                .getJobExecution()
                .getExecutionContext();

        this.covidCountryDataList = (List<CovidCountryDataDTO>) executionContext.get("covidDataList");
        countryDataList = new ArrayList<>();
    }

    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {

        this.covidCountryDataList.stream().forEach(covidCountryDataDTO -> {
            if (covidCountryDataDTO.getCountryCode().equalsIgnoreCase("TR")) {
                covidCountryDataDTO.setNewConfirmed(covidCountryDataDTO.getNewConfirmed() / 10);
                covidCountryDataDTO.setTotalConfirmed(covidCountryDataDTO.getTotalConfirmed() / 10);
            }
            countryDataList.add(covidCountryDataMapper.map(covidCountryDataDTO));
        });

        return RepeatStatus.FINISHED;
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        stepExecution
                .getJobExecution()
                .getExecutionContext()
                .put("processedCovidDataList", this.countryDataList);
        log.info("Lines Processor ended.");
        return ExitStatus.COMPLETED;
    }
}
