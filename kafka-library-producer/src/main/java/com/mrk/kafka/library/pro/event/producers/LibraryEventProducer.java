package com.mrk.kafka.library.pro.event.producers;

import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrk.kafka.library.pro.event.dtos.LibraryEvent;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LibraryEventProducer {

	private static final String topic="library_event";
	
	@Autowired
	public KafkaTemplate<String, String> kafkaTemplete;

	@Autowired
	public ObjectMapper objectMapper;

	public void sendEventToLibrary(LibraryEvent event) throws JsonProcessingException {
		ListenableFuture<SendResult<String, String>> result = kafkaTemplete.sendDefault(event.getLibraryEventId(),
				objectMapper.writeValueAsString(event));
		result.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {

			@Override
			public void onSuccess(SendResult<String, String> result) {
				handleSucces(event.getLibraryEventId(),result);

			}

			@Override
			public void onFailure(Throwable ex) {
				handleError(event.getLibraryEventId(),ex);

			}
		});
	}
	
	public SendResult<String, String> sendEventToLibraryApproch2(LibraryEvent event){
		
		try {
			SendResult<String, String> result=	kafkaTemplete.send(prepareProducerRecord(event)).get();
			return result;		
 		} catch (InterruptedException | ExecutionException e) {
			// TODO Auto-generated catch block
			log.error("Error occured {}",e);
		} 
		return new SendResult<>(null, null);
	}
	private ProducerRecord<String, String> prepareProducerRecord(LibraryEvent event){
		try {
			return new ProducerRecord<String, String>(topic, event.getLibraryEventId(), objectMapper.writeValueAsString(event));
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			log.error("Error occred {}", e);
		}
		return null;
	}
	private void handleSucces(String key,SendResult<String, String> result) {
		log.info("Message Key: {} and partition : {}", key, result.getRecordMetadata().partition());
	}
	
	private void handleError(String key,Throwable ex) {
		log.error("Error occured for key : {} and error message : {}", key, ex.getMessage());
	}
}
