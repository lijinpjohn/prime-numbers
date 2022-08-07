package com.pollinate.primenumbers.model.business.impl;

import com.pollinate.primenumbers.model.business.PrimeNumberAlgorithm;
import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.springframework.stereotype.Component;

import java.util.stream.LongStream;

@Component
public class BruteForceAlgorithmImpl implements PrimeNumberAlgorithm {
    /**
     * This checks a given number is prime using brute force algorithm
     * i.e. number = a * b. If both a and b were greater than the square root of n, a*b would be greater than n.
     * @param number
     * @return boolean
     */
    @Override
    public boolean isPrime(Long number) {
        return LongStream.rangeClosed(2L, (long) Math.sqrt(number))
                .allMatch(n -> number % n != 0);
    }

    @Override
    public String getType() {
        return ApplicationConstants.BRUTE_FORCE;
    }
}
