package com.rojmal.controller;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
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

	@RequestMapping(value = com.rojmal.EndPoint.UPDATE_REGISATION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Regisation update(@RequestBody Regisation regisation) {
		return regisationService.update( regisation);
	}

	@RequestMapping(value = com.rojmal.EndPoint.GET_REGISATION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Regisation getById(@PathVariable(value = "id") String id) {
		return regisationService.getById(id);
	}

	@RequestMapping("/")
	public Regisation insert1(@RequestBody Regisation regisation) {
		return regisationService.insert(regisation);
	}

	@RequestMapping(value = com.rojmal.EndPoint.GET_BY_USERNAME_REGISATION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Regisation get(
			@RequestParam(value = "username", required = false) String username) {
		return regisationService.get(username);
	}
	
	@RequestMapping(value = com.rojmal.EndPoint.GET_REGISATION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Regisation getById(@PathVariable(value = "id") String id) {
		return regisationService.getById(id);
	}

}
