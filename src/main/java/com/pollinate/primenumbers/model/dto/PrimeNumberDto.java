package com.pollinate.primenumbers.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data transfer object for prime numbers
 * Using lombok for generating getters, setters and constructors.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrimeNumberDto {

    private Long initial;

    private List<Long> primes;
}
