package com.rojmal.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rojmal.model.Regisation;
import com.rojmal.service.RegisationService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegisationApiController {
	
	@Inject
	RegisationService regisationService;
	
	@RequestMapping(value = com.rojmal.EndPoint.INSERT_REGISATION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
	public Regisation insert(@RequestBody Regisation regisation) {
        return regisationService.insert(regisation);
    }
	
		@RequestMapping("/")
		public Regisation insert1(@RequestBody Regisation regisation) {
	        return regisationService.insert(regisation);
	    }
	
}
