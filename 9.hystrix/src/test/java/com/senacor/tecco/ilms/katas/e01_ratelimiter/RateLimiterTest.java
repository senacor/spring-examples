package com.senacor.tecco.ilms.katas.e01_ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.senacor.tecco.ilms.katas.HystrixApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by fsubasi on 04.02.2016.
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = HystrixApplication.class)
@WebAppConfiguration
public class RateLimiterTest {

    @Test
    public void rateLimiterTest(){
        int rateLimiterCounter = 0;
        // distribute two permits every minute
        RateLimiter rateLimiter = RateLimiter.create(2);

        long start = System.nanoTime();
        long now = System.nanoTime();
        // run for three seconds
        while(now - start < 3_000_000_000L){
            rateLimiter.acquire();
            rateLimiterCounter++;
            now = System.nanoTime();
        }

        // If ratelimiter were not used, the counter would have a value much larger than 9
        Assert.assertTrue(rateLimiterCounter < 9);
    }
}
