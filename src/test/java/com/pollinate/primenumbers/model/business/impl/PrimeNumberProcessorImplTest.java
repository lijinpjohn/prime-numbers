package com.pollinate.primenumbers.model.business.impl;

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
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
public class PrimeNumberProcessorImplTest {

    @MockBean
    private PrimeNumberAlgorithmFactory primeNumberAlgorithmFactory;

    @MockBean
    private BruteForceAlgorithmImpl bruteForceAlgorithm;

    private PrimeNumberProcessorImpl primeNumberProcessor;

    @Before
    public void setUp(){
        primeNumberProcessor  = new PrimeNumberProcessorImpl();
        ReflectionTestUtils.setField(primeNumberProcessor,"primeNumberFactory", primeNumberAlgorithmFactory);
    }

    @Test
    public void processSuccessWithDefaultAlgorithmTest() throws ExecutionException, InterruptedException {
        List<Long> expected = Arrays.asList(3l,5l,7l);
        Mockito.when(primeNumberAlgorithmFactory.getPrimeNumberAlgorithm(ApplicationConstants.BRUTE_FORCE))
        .thenReturn(bruteForceAlgorithm);
        Mockito.when(bruteForceAlgorithm.isPrime(3l)).thenReturn(true);
        Mockito.when(bruteForceAlgorithm.isPrime(5l)).thenReturn(true);
        Mockito.when(bruteForceAlgorithm.isPrime(7l)).thenReturn(true);
        Mockito.when(bruteForceAlgorithm.isPrime(9l)).thenReturn(false);
        CompletableFuture<List<Long>> completableFuture = primeNumberProcessor.process(Arrays.asList(3l, 5l, 7l, 9l)
                , Optional.empty());
        List<Long> actual = completableFuture.get();
        Assert.assertEquals(expected,actual);
    }

    @Test
    public void processSuccessWithAlgorithmProvidedTest() throws ExecutionException, InterruptedException {
        List<Long> expected = Arrays.asList(3l,5l,7l);
        Mockito.when(primeNumberAlgorithmFactory.getPrimeNumberAlgorithm(ApplicationConstants.BRUTE_FORCE))
                .thenReturn(bruteForceAlgorithm);
        Mockito.when(bruteForceAlgorithm.isPrime(3l)).thenReturn(true);
        Mockito.when(bruteForceAlgorithm.isPrime(5l)).thenReturn(true);
        Mockito.when(bruteForceAlgorithm.isPrime(7l)).thenReturn(true);
        Mockito.when(bruteForceAlgorithm.isPrime(9l)).thenReturn(false);
        CompletableFuture<List<Long>> completableFuture = primeNumberProcessor.process(Arrays.asList(3l, 5l, 7l, 9l)
                , Optional.of(ApplicationConstants.BRUTE_FORCE));
        List<Long> actual = completableFuture.get();
        Assert.assertEquals(expected,actual);
    }

}
