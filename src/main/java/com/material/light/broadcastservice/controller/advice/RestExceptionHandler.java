package com.material.light.broadcastservice.controller.advice;

import com.material.light.broadcastservice.model.contract.GenericResponse;
import com.material.light.broadcastservice.model.enums.ResponseEnum;
import com.material.light.broadcastservice.model.exception.GenericException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by djames
 * 24/09/2019  6:42 PM
 */
@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(value = GenericException.class)
    protected ResponseEntity<GenericResponse> genericExceptionHandler(GenericException e) {
        log.error("Generic Exception Encountered: message: {} | causedBy: {}", e.getMessage(), e.getCause());
        log.error("", e);

        ResponseEntity<GenericResponse> response = ResponseEntity.status(e.getStatusCode()).body(new GenericResponse(e));
        log.error("Response: {}", response);
        return response;
    }

    @ExceptionHandler(value = Exception.class)
    protected ResponseEntity<GenericResponse> generalExceptionHandler(Exception e) {
        log.error("Exception Encountered: message: {} | causedBy: {}", e.getMessage(), e.getCause());
        log.error("", e);

        ResponseEntity<GenericResponse> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new GenericResponse(ResponseEnum.UNKNOWN_EXCEPTION));
        log.error("Response: {}", response);
        return response;
    }
}
