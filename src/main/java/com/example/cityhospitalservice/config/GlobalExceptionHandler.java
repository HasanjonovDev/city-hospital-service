package com.example.cityhospitalservice.config;

import com.ctc.wstx.shaded.msv.relaxng_datatype.DatatypeException;
import com.example.cityhospitalservice.exception.DataNotFoundException;
import com.example.cityhospitalservice.exception.NotAcceptable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {NotAcceptable.class})
    public ResponseEntity<String> notAcceptable(NotAcceptable e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(value = {DatatypeException.class})
    public ResponseEntity<String> dataNotFound(DataNotFoundException e){
        return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
    }
}
