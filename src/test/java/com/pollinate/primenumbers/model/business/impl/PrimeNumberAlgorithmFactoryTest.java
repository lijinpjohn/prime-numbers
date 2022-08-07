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
public class PrimeNumberAlgorithmFactoryTest {

    private PrimeNumberAlgorithmFactory primeNumberAlgorithmFactory;

    @Before
    public void setUp() throws Exception{
        primeNumberAlgorithmFactory = (PrimeNumberAlgorithmFactory)Class
                .forName("com.pollinate.primenumbers.model.business.impl.PrimeNumberAlgorithmFactory")
                .newInstance();
    }

    @Test
    public void isPrimeTrueTest() throws ExecutionException, InterruptedException {
        PrimeNumberAlgorithm actual = primeNumberAlgorithmFactory.getPrimeNumberAlgorithm(ApplicationConstants.BRUTE_FORCE);
        Assert.assertTrue(actual instanceof BruteForceAlgorithmImpl);
    }

}
