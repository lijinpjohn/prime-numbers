package com.pollinate.primenumbers.model.business.impl;

import com.pollinate.primenumbers.model.business.PrimeNumberAlgorithm;
import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.springframework.stereotype.Component;

@Component
public class CommonAlgorithmImpl implements PrimeNumberAlgorithm {
    /**
     * This checks a given number is prime using brute force algorithm
     * i.e. number = a * b. If both a and b were greater than the square root of n, a*b would be greater than n.
     * @param number
     * @return boolean
     */
    @Override
    public boolean isPrime(Long number) {
        for (Long i = 2l; i*i <= number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getType() {
        return ApplicationConstants.COMMON_ALGORITHM;
    }
}
