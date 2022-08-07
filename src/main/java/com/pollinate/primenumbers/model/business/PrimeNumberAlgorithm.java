package com.pollinate.primenumbers.model.business;

/**
 * Interface for prime number algorithms
 */
public interface PrimeNumberAlgorithm {

   boolean isPrime(Long number);

   String getType();

}
