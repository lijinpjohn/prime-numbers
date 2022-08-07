package com.pollinate.primenumbers.model.business.impl;

import com.pollinate.primenumbers.utils.ApplicationConstants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.ExecutionException;

@RunWith(SpringRunner.class)
public class BruteForceAlgorithmImplTest {

    private BruteForceAlgorithmImpl bruteForceAlgorithm;

    @Before
    public void setUp(){
        bruteForceAlgorithm  = new BruteForceAlgorithmImpl();
    }

    @Test
    public void isPrimeTrueTest() throws ExecutionException, InterruptedException {
        boolean actual = bruteForceAlgorithm.isPrime(5l);
        Assert.assertEquals(true,actual);
    }

    @Test
    public void isPrimeFalseTest() throws ExecutionException, InterruptedException {
        boolean actual = bruteForceAlgorithm.isPrime(4l);
        Assert.assertEquals(false,actual);
    }

    @Test
    public void getTypeTest() throws ExecutionException, InterruptedException {
        String actual = bruteForceAlgorithm.getType();
        Assert.assertEquals(ApplicationConstants.BRUTE_FORCE,actual);
    }

}
