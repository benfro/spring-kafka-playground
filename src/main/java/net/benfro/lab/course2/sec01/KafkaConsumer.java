package net.benfro.lab.course2.sec01;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.UnaryOperator;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.springframework.cloud.stream.binder.reactorkafka.ReceiverOptionsCustomizer;
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
            .doOnNext(s -> log.info("consumer received {}", s))
            .subscribe();
    }

    @Bean
    public Function<Flux<String>, Mono<Void>> function() {
        return flux -> flux
            .doOnNext(s -> log.info("consumer received {}", s))
            .then();
    }

//    @Bean
//    public ReceiverOptionsCustomizer<String, Object> customizer() {
//        // 's' is the binder name, ie 'function-in-0'
//        return (s, ro) -> ro.consumerProperty(ConsumerConfig.GROUP_INSTANCE_ID_CONFIG, "456");
//    }

}

