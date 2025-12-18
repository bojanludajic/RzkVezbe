package com.example.bojanludajic.exception;

import jakarta.persistence.EntityNotFoundException;
import org.jspecify.annotations.Nullable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorEntity> handleEntityNotFound(Exception e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorEntity(e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(EntityAlreadyExistsException.class)
    public ResponseEntity<ErrorEntity> handleEntityAlreadyExists(Exception e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new ErrorEntity(e.getMessage(), LocalDateTime.now()));
    }

    @ExceptionHandler(TImeNotValidException.class)
    public ResponseEntity<ErrorEntity> handleTImeNotValid(Exception e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorEntity(e.getMessage(), LocalDateTime.now()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ErrorEntity errorEntity = new ErrorEntity(ex.getFieldError().getField(), LocalDateTime.now());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorEntity);
    }
}
