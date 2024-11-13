package net.benfro.lab.course2.sec02;


import java.time.Duration;
import java.util.function.Supplier;

import org.apache.kafka.clients.producer.Producer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class KafkaProducer {

    @Bean
    public Supplier<Flux<String>> producer() {
        return () -> Flux.interval(Duration.ofMillis(1000))
            .take(10)
            .map(i -> "Message-" + i)
            .doOnNext(m -> log.info("produce {}",m));
    }
}
