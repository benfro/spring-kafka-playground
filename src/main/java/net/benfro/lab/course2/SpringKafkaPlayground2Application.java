package net.benfro.lab.course2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "net.benfro.lab.course2.${sec}")
public class SpringKafkaPlayground2Application {

	public static void main(String[] args) {
		SpringApplication.run(SpringKafkaPlayground2Application.class, args);
	}

}
