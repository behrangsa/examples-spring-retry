# Spring Retry Mini App

## Why?

You have a service that might throw transient exceptions. For example, you might have a service that invokes
an AWS API that might intermittently throw `ProvisionedThroughputExceededException` exceptions.

When such a transient exception occurs, you don't want your service to go cold turkey. Instead, you want to
retry the AWS API call a few times before giving up.

[Spring Retry](https://github.com/spring-projects/spring-retry) provides the necessary facilities to implement
retry logic in your Spring applications with ease.

## How?

### Enable Spring Retry

```java
@SpringBootApplication
@EnableRetry 
public class Main {
    
}
```

### Annotate your Retryable methods

```java
@Retryable(value = {RuntimeException.class}, maxAttempts = 5, backoff = @Backoff(value = 5))
public String eventuallyWorkingService(int succeedOnAttempt) {

}
```

### Test your Retryable methods

```java
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
```