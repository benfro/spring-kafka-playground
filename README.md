# Part 2 of reactive kafka courses

## Spring Cloud Stream
This is a generic framework for integrating a number of external queues and messaging components.
Setup is easiest configured with *.yaml file. 

Cloud Stream uses compnents called 'binders' and the binders and bindings are defined in a
*.yaml file.

Consumers and producers can be implemented with

```java
import org.springframework.context.annotation.Bean;

//@Bean
//public Producer<Flux<String>> producer() {}
@Bean
public Consumer<Flux<String>> consumer() {
    return flux -> flux
        .subscribe(); // etc.
}
```
OR by implementing functions
```java
@Bean
public Supplier<Flux<String>> producer() {
    return () -> Flux.just(1, 2); // etc.
}

@Bean
public Function<Flux<String>, Mono<Void>> consumer() {
    return flux -> flux
        .then(); // etc.
}
```

The consumer can be further configured by

```java
@Bean
public ReceiverOptionsCustomizer receiverCustomizer() {
    return (s, ro) -> ro; // etc.
}

@Bean
public SenderOptionsCustomizer senderCustomizer() {
    return (s, so) -> so.producerProperty("bb", "aa"); // etc.
}
```

### Integration tests
Use @EmbeddedKafka and provide local properties for every integration test
