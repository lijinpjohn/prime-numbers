package com.pollinate.primenumbers.service.impl;

import com.pollinate.primenumbers.configuration.ApplicationProperties;
import com.pollinate.primenumbers.exception.PrimeNumberException;
import com.pollinate.primenumbers.model.business.PrimeNumberProcessor;
import com.pollinate.primenumbers.model.dto.PrimeNumberDto;
import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

@RunWith(SpringRunner.class)
public class PrimeNumberServiceImplTest {

    @MockBean
    private PrimeNumberProcessor primeNumberProcessor;

    private PrimeNumberServiceImpl primeNumberService;

    private ApplicationProperties applicationProperties;

    @Before
    public void setUp(){
        primeNumberService  = new PrimeNumberServiceImpl();
        applicationProperties = new ApplicationProperties();
        applicationProperties.setChunkSize("5");
        ReflectionTestUtils.setField(primeNumberService,"primeNumberProcessor",primeNumberProcessor);
        ReflectionTestUtils.setField(primeNumberService,"applicationProperties",applicationProperties);
    }

    @Test
    public void calculatePrimeNumberInvalidNumberTest(){
        PrimeNumberException primeNumberException = Assert.assertThrows(
                PrimeNumberException.class,
                () -> primeNumberService.calculatePrimeNumber("INVALID", Optional.empty()));
        Assert.assertEquals(ApplicationConstants.INVALID_NUMBER,primeNumberException.getMessage());
    }

    @Test
    public void calculatePrimeNumberLessThanTwoTest(){
        PrimeNumberException primeNumberException = Assert.assertThrows(
                PrimeNumberException.class,
                () -> primeNumberService.calculatePrimeNumber("1", Optional.empty()));
        Assert.assertEquals(ApplicationConstants.INVALID_RANGE,primeNumberException.getMessage());
    }

    @Test
    public void calculatePrimeNumberAlgorithmInvalidTest(){
        PrimeNumberException primeNumberException = Assert.assertThrows(
                PrimeNumberException.class,
                () -> primeNumberService.calculatePrimeNumber("2", Optional.of("INVALID")));
        Assert.assertEquals(ApplicationConstants.INVALID_ALGORITHM,primeNumberException.getMessage());
    }

    @Test
    public void calculatePrimeNumberSuccessTest(){
        PrimeNumberDto expected = getPrimeNumberDto();
        expected.setInitial(10l);
        expected.setPrimes(Arrays.asList(2l,3l,5l,7l));
        Mockito.when(primeNumberProcessor.process(Arrays.asList(2l), Optional.empty()))
        .thenReturn(CompletableFuture.completedFuture(Arrays.asList(2l)));
        Mockito.when(primeNumberProcessor.process(Arrays.asList(3l,5l,7l,9l), Optional.empty()))
        .thenReturn(CompletableFuture.completedFuture(Arrays.asList(3l,5l,7l)));
        PrimeNumberDto actual = primeNumberService.calculatePrimeNumber("10", Optional.empty());
        Assert.assertEquals(expected,actual);
    }

    private PrimeNumberDto getPrimeNumberDto() {
        PrimeNumberDto primeNumberDto = new PrimeNumberDto();
        primeNumberDto.setInitial(10l);
        primeNumberDto.setPrimes(Arrays.asList(2l,3l,5l,7l));
        return primeNumberDto;
    }
}
