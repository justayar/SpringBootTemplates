package com.justayar.springboot.dto;

import lombok.*;
import java.sql.Timestamp;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
public class CovidSummaryDTO {

    private String message;
    private CovidSummaryGlobalDTO global;
    private List<CovidCountryDataDTO> countries;
    private Timestamp date;
}
