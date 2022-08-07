package com.pollinate.primenumbers.controller;

import com.pollinate.primenumbers.model.dto.PrimeNumberDto;
import com.pollinate.primenumbers.service.PrimeNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 *  Rest controller for prime numbers
 */
@RestController
public class PrimeNumberController {

    @Autowired
    private PrimeNumberService primeNumberService;

    /**
     * This method accepts an initial and generate prime number up to that.
     * @param initial
     * @return ResponseEntity
     */
    @GetMapping("/primes/{initial}")
    public ResponseEntity<PrimeNumberDto> getPrimeNumbers(@PathVariable(value = "initial") Long initial, @RequestParam Optional<String> algorithm) {
        return ResponseEntity.accepted().body(primeNumberService.calculatePrimeNumber(initial,algorithm));
    }
}
