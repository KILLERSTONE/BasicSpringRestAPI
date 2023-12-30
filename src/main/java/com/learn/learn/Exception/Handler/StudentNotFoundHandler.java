package com.learn.learn.Exception.Handler;

import java.sql.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.NotFound;

import com.learn.learn.Exception.StudentNotFoundException;
import com.learn.learn.Model.ErrorType;

@RestControllerAdvice
public class StudentNotFoundHandler {
    

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<ErrorType> handlerNotFound(StudentNotFoundException err){

        return new ResponseEntity<ErrorType>(
            new ErrorType(
                new Date(System.currentTimeMillis()).toString(),
                "404- NOT FOUND",
                err.getMessage()
                ),
            HttpStatus.NOT_FOUND);
            
        
        
    }
}
