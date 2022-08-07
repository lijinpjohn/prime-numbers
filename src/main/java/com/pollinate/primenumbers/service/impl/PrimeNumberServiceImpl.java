package com.pollinate.primenumbers.service.impl;

import com.pollinate.primenumbers.configuration.ApplicationProperties;
import com.pollinate.primenumbers.exception.PrimeNumberException;
import com.pollinate.primenumbers.model.business.PrimeNumberProcessor;
import com.pollinate.primenumbers.model.dto.PrimeNumberDto;
import com.pollinate.primenumbers.service.PrimeNumberService;
import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

/**
 * Service class for prime number calculation
 */
@Service
public class PrimeNumberServiceImpl implements PrimeNumberService {

    @Autowired
    private PrimeNumberProcessor primeNumberProcessor;

    @Autowired
    ApplicationProperties applicationProperties;

    /**
     * This method generate the list of prime numbers less than or equal to given number.
     * @param initial holding the input
     * @return primeNumberDto
     */
    @Cacheable(ApplicationConstants.PRIMES)
    public PrimeNumberDto calculatePrimeNumber(Long initial, Optional<String> algorithm) {
        validateInputs(initial,algorithm);
        PrimeNumberDto primeNumberDto = new PrimeNumberDto();
        primeNumberDto.setInitial(initial);
        // Here we set the prime number data returned to dto
        primeNumberDto.setPrimes(getPrimeNumbers(initial,algorithm));
        return primeNumberDto;
    }

    /**
     * This method divides the input to chunks the process with multiple threads.
     * Then consolidate and extract the data in to required format.
     * @param initial holding the user input
     * @return list of prime numbers
     */
    private List<Long> getPrimeNumbers(Long initial, Optional<String> algorithm) {
        List<CompletableFuture<List<Long>>> completableFutures = new ArrayList<>();
        // Split it in to chunk and delegate to multiple threads
        for (List<Long> chunk : splitItToChunks(initial)) {
            CompletableFuture<List<Long>> requestCompletableFuture = primeNumberProcessor.process(chunk, algorithm);
            completableFutures.add(requestCompletableFuture);
        }
        // waiting to get all the threads to complete
        CompletableFuture.allOf(completableFutures.toArray(new CompletableFuture[0]))
                // avoid throwing an exception in the join() call
                .exceptionally(ex -> null)
                .join();
        // extracting the data from completable future and returning here
        return extractResult(completableFutures).stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    /**
     * This method is used to extract data in the required format
     * @param completableFutures
     * @return List<List<Long>>
     */
    private List<List<Long>> extractResult(List<CompletableFuture<List<Long>>> completableFutures) {
        // Partitioning the records with and without Exception
        Map<Boolean, List<CompletableFuture<List<Long>>>> result = completableFutures.stream()
                        .collect(Collectors.partitioningBy(CompletableFuture::isCompletedExceptionally));
        // Then taking the record without exception
        return result.get(false).stream()
                .map(p -> {
                    try {
                        return p.get();
                    } catch (InterruptedException e) {
                        throw new PrimeNumberException(ApplicationConstants.EXECUTION_INTERRUPTED);
                    } catch (ExecutionException e) {
                        throw new PrimeNumberException(ApplicationConstants.SOMETHING_WRONG);
                    }
                }).collect(Collectors.toList());
    }

    /**
     * This method splits the initial provided in to multiple chunk.
     * The chunk size can be set in application.properties
     * @param initial
     * @return List<List<Long>>
     */
    private List<List<Long>> splitItToChunks(Long initial) {
        final AtomicLong counter = new AtomicLong();
        final Long chunkSize = Long.parseLong(applicationProperties.getChunkSize());
        final ArrayList<List<Long>> result = new ArrayList<>();
        if(initial>=2l){
            result.add(new ArrayList<>());
            result.get(result.size() - 1).add(2l);
        }
        for (Long i = 3l; i <= initial; i+=2l) {
            if (counter.getAndIncrement() % chunkSize == 0) {
                result.add(new ArrayList<>());
            }
            result.get(result.size() - 1).add(i);
        }
        return result;
    }


    private void validateInputs(Long initial, Optional<String> algorithm) {
        if(!StringUtils.isNumeric(initial.toString())){
            throw new PrimeNumberException(ApplicationConstants.INVALID_NUMBER);
        } else if(initial < 2l){
            throw new PrimeNumberException(ApplicationConstants.INVALID_RANGE);
        } else if(algorithm.isPresent()
                && (!StringUtils.isAlpha(algorithm.get())
                    || algorithm.get().length() != 2
                    || !algorithm.get().equals(ApplicationConstants.BRUTE_FORCE))){
            throw new PrimeNumberException(ApplicationConstants.INVALID_ALGORITHM);
        }
    }

}
