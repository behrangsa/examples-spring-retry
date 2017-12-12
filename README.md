# Spring Retry Mini App

## Why?

You have a service that might throw transient exceptions. For example, you might have a service that invokes
an AWS API that might intermittently throw `ProvisionedThroughputExceededException` exceptions.

When such a transient exception occurs, you don't want your service to go cold turkey. Instead, you want to
retry the AWS API call a few times before giving up.

[Spring Retry](https://github.com/spring-projects/spring-retry) provides the necessary facilities to implement
retry logic in your Spring applications with ease.

## How?

### 1. Enable Spring Retry

https://github.com/behrangsa/examples-spring-retry/blob/b3b00355b5a03df249e84350d774343247d0e98f/src/main/java/org/behrang/examples/spring/retry/Main.java#L7-L9

### 2. Annotate your Retryable methods

https://github.com/behrangsa/examples-spring-retry/blob/b3b00355b5a03df249e84350d774343247d0e98f/src/main/java/org/behrang/examples/spring/retry/SampleService.java#L16-L26

### 3. Test your Retryable methods

https://github.com/behrangsa/examples-spring-retry/blob/b3b00355b5a03df249e84350d774343247d0e98f/src/test/java/org/behrang/examples/spring/retry/SampleServiceTests.java#L24-L33
