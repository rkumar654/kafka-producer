package com.mrk.kafka.library.pro.rest.apis;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrk.kafka.library.pro.event.dtos.Book;
import com.mrk.kafka.library.pro.event.dtos.LibraryEvent;
import com.mrk.kafka.library.pro.event.dtos.LibraryEventType;
import com.mrk.kafka.library.pro.event.producers.LibraryEventProducer;

@RestController
@RequestMapping("api/")
public class LibraryRestController {
	
	@Autowired
	private LibraryEventProducer libraryEventProducer;

	@PostMapping("v1/library")
	public ResponseEntity<?> save(@RequestBody Book book) throws JsonProcessingException {
		LibraryEvent event = new LibraryEvent(UUID.randomUUID().toString(),LibraryEventType.NEW, book);
		libraryEventProducer.sendEventToLibrary(event);
		return new ResponseEntity(event, HttpStatus.ACCEPTED);
	}
	
	@PostMapping("v2/library")
	public ResponseEntity<?> save1(@RequestBody Book book) throws JsonProcessingException {
		LibraryEvent event = new LibraryEvent(UUID.randomUUID().toString(),LibraryEventType.NEW, book);
		libraryEventProducer.sendEventToLibraryApproch2(event);
		return new ResponseEntity(event, HttpStatus.CREATED);
	}

}
