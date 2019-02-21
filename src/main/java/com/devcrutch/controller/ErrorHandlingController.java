package com.devcrutch.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorHandlingController {

    private ExceptionMessage createExceptionMessage(Exception e) {
        ExceptionMessage em = new ExceptionMessage();
        em.setCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        em.setDescription(e.getMessage());
        return em;
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionMessage> handleGeneralException(Exception e) throws Exception{
        return new ResponseEntity<>(createExceptionMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = UserNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleUserNotFoundException(UserNotFoundException e) throws UserNotFoundException {
        return new ResponseEntity<>(createExceptionMessage(e), HttpStatus.INTERNAL_SERVER_ERROR);
    }


    class ExceptionMessage {
        private int code;
        private String description;

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }
    }
}
