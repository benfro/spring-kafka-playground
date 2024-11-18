package net.benfro.lab.course2.sec02;

import java.util.function.Consumer;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.system.OutputCaptureExtension;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.TestPropertySource;

import lombok.extern.slf4j.Slf4j;
import net.benfro.lab.course2.AbstractIntegrationTest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Sinks;

@Slf4j
@TestPropertySource(properties = {
    "sec=sec02",
    "spring.cloud.function.definition=consumer;testProducer",
    "spring.cloud.stream.bindings.testConsumer-in-0.destination=user-events",
    "logging.level.root=ERROR",
    "logging.level.net.benfro*=INFO"
})
@ExtendWith(OutputCaptureExtension.class)
public class KafkaProducerTest extends AbstractIntegrationTest {

    private static final Sinks.Many<String> sink = Sinks.many().multicast().onBackpressureBuffer();


    static class TestConfig {

        @Bean
        public Consumer<Flux <String>> testConsumer() {
            return f -> f.doOnNext(sink::tryEmitNext).subscribe();
        }

    }


}
