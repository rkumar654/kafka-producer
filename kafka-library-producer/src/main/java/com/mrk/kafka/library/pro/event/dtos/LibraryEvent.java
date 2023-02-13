package com.mrk.kafka.library.pro.event.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class LibraryEvent implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8095293632054991912L;

	private String libraryEventId;
	private LibraryEventType eventType;
	private Book bookDtls;
}
