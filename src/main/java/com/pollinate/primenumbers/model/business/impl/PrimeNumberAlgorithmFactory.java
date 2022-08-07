package com.pollinate.primenumbers.model.business.impl;

import com.pollinate.primenumbers.exception.PrimeNumberException;
import com.pollinate.primenumbers.model.business.PrimeNumberAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *  Factory for various algorithms for generating prime numbers
 */
@Component
public class PrimeNumberAlgorithmFactory {

    // For keeping a cache for instance of all algorithms
    private static final Map<String, PrimeNumberAlgorithm> primeNumberAlgorithmCache = new HashMap<>();

    /**
     * Here we initialise various algorithms with autowired constructor
     * @param primeNumberAlgorithms
     */
    @Autowired
    private PrimeNumberAlgorithmFactory(List<PrimeNumberAlgorithm> primeNumberAlgorithms) {
        for(PrimeNumberAlgorithm primeNumberAlgorithm : primeNumberAlgorithms) {
            primeNumberAlgorithmCache.put(primeNumberAlgorithm.getType(), primeNumberAlgorithm);
        }
    }

    /**
     * This method returns required algorithms based on the types provided
     * @param type
     * @return primeNumberAlgorithm
     * @throws PrimeNumberException
     */
    public PrimeNumberAlgorithm getPrimeNumberAlgorithm(String type) {
        PrimeNumberAlgorithm primeNumberAlgorithm = primeNumberAlgorithmCache.get(type);
        if(primeNumberAlgorithm == null) {
            // Checking for invalid algorithm type
            throw new PrimeNumberException("Algorithm type, '"+ type +"' is not valid");
        }
        return primeNumberAlgorithm;
    }



}
