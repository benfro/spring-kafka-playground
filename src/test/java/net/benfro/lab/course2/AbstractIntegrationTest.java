package net.benfro.lab.course2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;

@SpringBootTest
@EmbeddedKafka(
    partitions = 1,
    bootstrapServersProperty = "spring.kafka.bootstrap-servers"
//    topics = { AbstractKafkaIT.TOPIC_AT_HAND }
)
public abstract class AbstractIntegrationTest {

//    public static String TOPIC_AT_HAND = "topic-at-hand";

    @Autowired
    protected EmbeddedKafkaBroker broker;
}
