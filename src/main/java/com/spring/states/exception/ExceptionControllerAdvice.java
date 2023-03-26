package com.spring.states.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    private static final Log logger = LogFactory.getLog(ExceptionControllerAdvice.class);

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> globalExceptionHandler(Exception exception){
        logger.error("Unexpected error", exception);
        ErrorInfo errorInfo = new ErrorInfo(HttpStatus.INTERNAL_SERVER_ERROR.value(), exception.getMessage());
        return new ResponseEntity<>(errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
