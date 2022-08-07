package com.pollinate.primenumbers.model.business.impl;

import com.pollinate.primenumbers.model.business.PrimeNumberProcessor;
import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * This class process individual chunks.
 */
@Component
public class PrimeNumberProcessorImpl implements PrimeNumberProcessor {

    @Autowired
    private PrimeNumberAlgorithmFactory primeNumberAlgorithmFactory;

    /**
     * This is the asynchronous method that generates prime numbers of the given range.
     * A new or free thread from the pool is assigned when this method is called.
     * @param list Takes a list of input.
     * @return CompletableFuture<List<Long>> only returns prime numbers from the list provided.
     */
    @Async
    public CompletableFuture<List<Long>> process(List<Long> list, Optional<String> algorithm) {
        List<Long> primeNumbers = new LinkedList<>();
        // Taking brute force algorithm as default
        if(!algorithm.isPresent()){
            algorithm = Optional.of(ApplicationConstants.BRUTE_FORCE);
        }
        if (list != null && !list.isEmpty()) {
            for (Long number : list) {
                if (primeNumberAlgorithmFactory.getPrimeNumberAlgorithm(algorithm.get()).isPrime(number)) {
                    primeNumbers.add(number);
                }
            }
        }
        return CompletableFuture.completedFuture(primeNumbers);
    }


}
