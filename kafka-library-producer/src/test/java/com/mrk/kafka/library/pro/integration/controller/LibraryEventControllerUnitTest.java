package com.mrk.kafka.library.pro.integration.controller;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import com.mrk.kafka.library.pro.rest.apis.LibraryRestController;

@WebMvcTest(LibraryRestController.class)
@AutoConfigureMockMvc
public class LibraryEventControllerUnitTest {

}
