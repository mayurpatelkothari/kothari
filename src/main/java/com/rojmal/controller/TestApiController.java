package com.rojmal.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rojmal.model.Regisation;
import com.rojmal.service.RegisationService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TestApiController {

	@Inject
	RegisationService registrationService;

	@RequestMapping("/info")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/data")
	public String insert() {
		return "Greetings from Spring data!";
	}

	@RequestMapping(value = "/data/currentUser", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody
	public Regisation getUser() {
		System.out.println("-----------------------mayur kothari-----------------------");

		return registrationService.currentUser();

	}
}
