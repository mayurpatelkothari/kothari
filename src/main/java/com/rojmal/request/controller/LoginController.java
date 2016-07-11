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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.rojmal.EndPoint;
import com.rojmal.security.SecurityServiceHelper;

/**
 * This class is used for the login controller
 * 
 * @author Jaydeep.
 */
@Controller
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class LoginController {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(LoginController.class);

	@Inject
	private Environment env;
	@Inject
	private SecurityServiceHelper securityServiceHelper;

	@RequestMapping(value = EndPoint.LOGIN, method = RequestMethod.GET)
	public ModelAndView login(
			@RequestParam(value = "error", required = false) String error,
			@RequestParam(value = "logout", required = false) String logout) {

		ModelAndView model = new ModelAndView();

		if (error != null) {
			model.addObject("error", "Invalid username and password!");
			System.out.println("Invalid username and password!");

		}

		if (logout != null) {
			// model.addObject("msg", "You've been logged out successfully.");
			securityServiceHelper.logout();
			System.out.println("User sucessfully logout!");
		}
		model.setViewName("login");

		return model;
	}
}
