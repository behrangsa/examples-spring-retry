package org.behrang.examples.spring.retry;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleServiceTests {

    @Autowired
    private SampleService sampleService;

    @Before
    public void before() {
        sampleService.resetCount();
    }

    @Test
    public void shouldAttemptUpToFiveTimes() {
        final String result = sampleService.eventuallyWorkingService(5);
        assertThat(result).isEqualTo("Call count: 5");
    }

    @Test(expected = RuntimeException.class)
    public void shouldFailOnSixthCall() {
        sampleService.eventuallyWorkingService(6);
    }

}
