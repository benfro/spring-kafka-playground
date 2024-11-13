package net.benfro.lab.course2.sec02;

import java.util.function.Consumer;
import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Slf4j
@Configuration
public class KafkaConsumer {

    @Bean
    public Consumer<Flux<String>> consumer() {
        return flux -> flux
            .doOnNext(s -> log.info("Consumer received: {}", s))
            .subscribe();
    }

}

