package com.pollinate.primenumbers.exception;

/**
 *  Custom exception class for this application
 *  This extends Exception class and accept error message as param.
 */
public class PrimeNumberException extends RuntimeException{

    public PrimeNumberException(String errorMessage) {
        super(errorMessage);
    }
}
