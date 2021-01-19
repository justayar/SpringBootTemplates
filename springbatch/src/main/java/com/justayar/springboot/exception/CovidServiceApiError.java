package com.justayar.springboot.exception;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class CovidServiceApiError {

    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private CovidServiceApiError() {
        timestamp = LocalDateTime.now();
    }

    CovidServiceApiError(HttpStatus status) {
        this();
        this.status = status;
    }

}