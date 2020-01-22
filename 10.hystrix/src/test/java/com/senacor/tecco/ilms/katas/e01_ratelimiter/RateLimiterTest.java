package com.senacor.tecco.ilms.katas.e01_ratelimiter;

import com.google.common.util.concurrent.RateLimiter;
import com.senacor.tecco.ilms.katas.HystrixApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by fsubasi on 04.02.2016.
 */

@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = HystrixApplication.class)
class RateLimiterTest {

    @Test
    void rateLimiterTest() {
        int rateLimiterCounter = 0;
        // distribute two permits every minute
        RateLimiter rateLimiter = RateLimiter.create(2);

        long start = System.nanoTime();
        long now = System.nanoTime();
        // run for three seconds
        while (now - start < 3_000_000_000L) {
            rateLimiter.acquire();
            rateLimiterCounter++;
            now = System.nanoTime();
        }

        // If ratelimiter were not used, the counter would have a value much larger than 9
        assertThat(rateLimiterCounter).isLessThan(9);
    }
}
