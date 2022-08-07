package com.pollinate.primenumbers.model.business;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

/**
 * Interface for prime number algorithms
 */
public interface PrimeNumberProcessor {

   CompletableFuture<List<Long>> process(List<Long> list, Optional<String> algorithm);

}
