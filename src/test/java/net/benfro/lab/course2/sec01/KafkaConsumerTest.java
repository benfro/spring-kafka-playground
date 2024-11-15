package net.benfro.lab.course2.sec01;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.Duration;
import java.util.function.Supplier;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.system.CapturedOutput;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import lombok.extern.slf4j.Slf4j;
import net.benfro.lab.course2.AbstractIntegrationTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

@Slf4j
@TestPropertySource(properties = {
    "sec=sec01",
    "spring.cloud.function.definition=consumer;testProducer",
    "spring.cloud.stream.bindings.testProducer-out-0.destination=user-events"
})
@ExtendWith(OutputCaptureExtension.class)
class KafkaConsumerTest extends AbstractIntegrationTest {

//    @BeforeAll
//    static void setUpAll() {
//        TOPIC_AT_HAND = "user-events";
//    }


    @Test
    void consumerTest(CapturedOutput output) {

        Mono.delay(Duration.ofMillis(500))
            .then(Mono.fromSupplier(output::getOut))
            .log("QQQ")
            .doOnNext(i -> log.info("QQQ gives: {}", i))
            .as(StepVerifier::create)
            .consumeNextWith(s -> assertTrue(s.contains("consumer received hello world")))
            .verifyComplete();
    }

    @TestConfiguration
    static class TestConfig {

        @Bean
        public Supplier<Flux<String>> testProducer() {
            return () -> Flux.just("hello world");
        }

    }
}
