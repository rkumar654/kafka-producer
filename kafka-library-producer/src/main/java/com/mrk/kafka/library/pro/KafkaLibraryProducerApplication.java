package com.mrk.kafka.library.pro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.mrk*")
public class KafkaLibraryProducerApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaLibraryProducerApplication.class, args);
	}

}
