package org.behrang.examples.spring.retry;

import lombok.extern.log4j.Log4j2;
import org.springframework.retry.annotation.Backoff;
import org.springframework.retry.annotation.Retryable;
import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicInteger;

@Component
@Log4j2
public class SampleService {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(value = 5))
    public String eventuallyWorkingService(int succeedOnAttempt) {
        final int callCount = counter.incrementAndGet();
        log.info("Call count: {}", callCount);

        if (callCount == succeedOnAttempt) {
            return String.format("Call count: %s", callCount);
        }

        throw new RuntimeException("I will eventually work.");
    }

    public void resetCount() {
        counter.set(0);
    }

}
