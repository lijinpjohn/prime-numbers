package com.pollinate.primenumbers.model.business.impl;

import com.pollinate.primenumbers.model.business.PrimeNumberAlgorithm;
import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
public class PrimeNumberFactoryTest {

    private PrimeNumberFactory primeNumberFactory;

    @Before
    public void setUp() throws Exception{
        primeNumberFactory = (PrimeNumberFactory)Class
                .forName("com.pollinate.primenumbers.model.business.impl.PrimeNumberFactory")
                .newInstance();
    }

    @Test
    public void isPrimeTrueTest() throws ExecutionException, InterruptedException {
        PrimeNumberAlgorithm actual = primeNumberFactory.getPrimeNumberAlgorithm(ApplicationConstants.BRUTE_FORCE);
        Assert.assertTrue(actual instanceof BruteForceAlgorithmImpl);
    }

}
