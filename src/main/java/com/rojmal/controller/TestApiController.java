package com.rojmal.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestApiController {
	@RequestMapping("/info")
    public String index() {
        return "Greetings from Spring Boot!";
    }
	
	@RequestMapping("/data")
    public String insert() {
        return "Greetings from Spring data!";
    }
}
