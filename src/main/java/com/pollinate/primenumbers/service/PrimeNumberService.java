package com.pollinate.primenumbers.service;

import com.pollinate.primenumbers.model.dto.PrimeNumberDto;

import java.util.Optional;

/**
 * Interface for prime number service.
 */
public interface PrimeNumberService {

    PrimeNumberDto calculatePrimeNumber(String initial, Optional<String> algorithm);

}
