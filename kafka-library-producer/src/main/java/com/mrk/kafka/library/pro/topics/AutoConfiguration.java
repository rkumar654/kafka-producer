package com.mrk.kafka.library.pro.topics;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.config.TopicBuilder;
import org.springframework.stereotype.Component;

@Component
@Profile("local")
public class AutoConfiguration {

	@Bean
	public NewTopic libraryTopic() {
		
		return TopicBuilder.name("library_event")
				.partitions(3)
				.replicas(3)
				.build();
	}
	
	/*
	 * @Bean public Map<String, Object> producerConfigs() { Map<String, Object>
	 * props = new HashMap<>(); props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,
	 * "localhost:9092,localhost:9093,localhost:9094");
	 * props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class);
	 * props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
	 * StringSerializer.class); return props; }
	 */
}
