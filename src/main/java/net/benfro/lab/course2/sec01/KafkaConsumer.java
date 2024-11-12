package net.benfro.lab.course2.sec01;

import java.util.function.Consumer;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Flux;

@Slf4j
@Configuration
public class KafkaConsumer {

    @Bean
    public Consumer<Flux<String>> consumer() {
        return flux -> flux
            .doOnNext(s -> log.info("Received: {}", s))
            .subscribe();
    }

}

