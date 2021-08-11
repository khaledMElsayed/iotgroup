package com.vf.iotgroup.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class IoTDocumentExceptionAdvice {

    @ExceptionHandler(value = InvalidIoTDocumentException.class)
    public ResponseEntity<Object> invalidIoTDocumentException(InvalidIoTDocumentException exception) {
        return new ResponseEntity<>("Invalid Device", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidManagerKeyException.class)
    public ResponseEntity<Object> invalidIpManagerKeyException(InvalidManagerKeyException exception) {
        return new ResponseEntity<>("Invalid Manager Key", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidTemperatureException.class)
    public ResponseEntity<Object> invalidIoTemperatureException(InvalidTemperatureException exception) {
        return new ResponseEntity<>("Temperature must be numbers only", HttpStatus.NOT_FOUND);
    }

}
