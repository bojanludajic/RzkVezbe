package com.example.bojanludajic.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class ErrorEntity {

    private String message;
    private LocalDateTime timestamp;

}
