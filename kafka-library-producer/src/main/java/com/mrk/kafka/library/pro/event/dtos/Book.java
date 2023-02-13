package com.mrk.kafka.library.pro.event.dtos;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
@Builder
public class Book implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5803598033354454725L;
	
	private int bookId;
	
	private String bookName;
	
	private String bookAuthor;

}
