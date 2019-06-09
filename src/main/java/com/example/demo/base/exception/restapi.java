package com.example.demo.base.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class restapi extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRundtimeException(RuntimeException ex, WebRequest request) {
        return super.handleExceptionInternal(ex, "runtimeException", null, HttpStatus.CONFLICT, request);
    }

////    @Override
////    protected ResponseEntity<Object> handleMethodArgumentNotValid(RuntimeException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
////        return super.handleExceptionInternal(ex, "MethodArgumentNotValidException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
////    }
//
//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) {
//        return super.handleExceptionInternal(ex, "handleAllException", null, HttpStatus.INTERNAL_SERVER_ERROR, request);
//    }

}
