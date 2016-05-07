package com.rojmal.request.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RestController;

import com.rojmal.service.RegisationService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegisationRequestController {

	@Autowired
	private RegisationService regisationService;

	// @RequestMapping(value = "/home", method = RequestMethod.GET)
	// public ModelAndView home() {
	// ModelAndView model = new ModelAndView();
	// Registration loginUser = registrationService.getCurrentUser();
	// if (loginUser != null) {
	// model.addObject("userName", loginUser.getUserName());
	// model.addObject("msg", "welcome user");
	// }
	// return model;
	// }

	// @RequestMapping(value = "/admin")
	// public ModelAndView login() {
	// ModelAndView model = new ModelAndView();
	// Registration loginUser = registrationService.getCurrentUser();
	// if (loginUser != null) {
	// model.addObject("userName", loginUser.getUserName());
	// }
	// return model;
	// }
}
