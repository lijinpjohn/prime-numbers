package com.pollinate.primenumbers.exception;

import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Collections;
import java.util.List;

/**
 * This class acts as a global handler for exceptions.
 */
@ControllerAdvice
public class PrimeControllerAdvice {

    /**
     * This method will capture all PrimeNumberException
     * This list is returned after wrapping it in ResponseEntity
     * @param e PrimeNumberException
     * @return ResponseEntity<List<String>>
     */
    @ExceptionHandler(PrimeNumberException.class)
    public ResponseEntity<List<String>> handleException(PrimeNumberException e) {
        return ResponseEntity.badRequest().body(Collections.singletonList(e.getMessage()));
    }

    /**
     * This method will capture all other exceptions
     * This list is returned after wrapping it in ResponseEntity
     * @param e
     * @return
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<List<String>> handleException(Exception e) {
        return ResponseEntity.badRequest().body(Collections.singletonList(ApplicationConstants.GENERAL_ERROR));
    }

}
