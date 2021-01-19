package com.justayar.springboot.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class ApplicationProperties {

    @Valid
    public CovidApiProvider covidApiProvider = new CovidApiProvider();

    public String csvFilePath;

    @Getter
    @Setter
    @NoArgsConstructor
    public class CovidApiProvider{
        @NotNull
        String serviceUrl;
        @NotNull
        String accessToken;
    }
}
