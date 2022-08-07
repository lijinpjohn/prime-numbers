package com.pollinate.primenumbers.controller;

import com.pollinate.primenumbers.model.dto.PrimeNumberDto;
import com.pollinate.primenumbers.service.PrimeNumberService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Optional;

@RunWith(SpringRunner.class)
public class PrimeNumberControllerTest {

    @MockBean
    private PrimeNumberService primeNumberService;

    private PrimeNumberController primeNumberController;

    @Before
    public void setUp(){
        primeNumberController  = new PrimeNumberController();
        ReflectionTestUtils.setField(primeNumberController,"primeNumberService",primeNumberService);
    }

    @Test
    public void getPrimeNumbersTest(){
        PrimeNumberDto primeNumberDto = new PrimeNumberDto();
        primeNumberDto.setInitial(10l);
        primeNumberDto.setPrimes(Arrays.asList(2l,3l,5l,7l));
        Mockito.when(primeNumberService.calculatePrimeNumber(2l,null)).thenReturn(primeNumberDto);
        ResponseEntity<PrimeNumberDto> primeNumbers = primeNumberController.getPrimeNumbers(2l, null);
        Assert.assertEquals(HttpStatus.ACCEPTED,primeNumbers.getStatusCode());
        Assert.assertEquals(primeNumbers.getBody(),primeNumberDto);
    }

    @Test
    public void getPrimeNumbersWithAlgorithmTest(){
        PrimeNumberDto primeNumberDto = new PrimeNumberDto();
        primeNumberDto.setInitial(10l);
        primeNumberDto.setPrimes(Arrays.asList(2l,3l,5l,7l));
        Mockito.when(primeNumberService.calculatePrimeNumber(2l, Optional.of("BF"))).thenReturn(primeNumberDto);
        ResponseEntity<PrimeNumberDto> primeNumbers = primeNumberController.getPrimeNumbers(2l, Optional.of("BF"));
        Assert.assertEquals(HttpStatus.ACCEPTED,primeNumbers.getStatusCode());
        Assert.assertEquals(primeNumbers.getBody(),primeNumberDto);
    }

}
