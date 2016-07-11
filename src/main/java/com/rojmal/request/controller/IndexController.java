package com.rojmal.request.controller;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.rojmal.EndPoint;

/**
 * This class is used for the login controller
 * 
 * @author Jaydeep.
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class IndexController {

	private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

	@Inject
	private Environment env;
	
	@RequestMapping(value = EndPoint.INDEX, method = RequestMethod.GET)
	public ModelAndView login() {

		ModelAndView model = new ModelAndView();
		
		model.setViewName("index");

		return model;
	}
}
