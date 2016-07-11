package com.rojmal.controller;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.rojmal.model.BirthRegister;
import com.rojmal.service.BirthRegisterService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BirthRegisterApiController {
	@Inject
	BirthRegisterService birthRegisterService;

	@RequestMapping(value = com.rojmal.EndPoint.INSERT_BIRTH_REGISATION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BirthRegister insert(@RequestBody BirthRegister birthRegister) {
		return birthRegisterService.insert(birthRegister);
	}

	@RequestMapping(value = com.rojmal.EndPoint.UPDATE_BIRTH_REGISATION, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BirthRegister update(@PathVariable(value = "id") String id,
			@RequestBody BirthRegister birthRegister) {
		return birthRegisterService.update(id,birthRegister);
	}

	@RequestMapping(value = com.rojmal.EndPoint.DELETE_BIRTH_REGISATION, method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(@PathVariable(value = "id") String id) {
		birthRegisterService.delete(id);	
	}

	@RequestMapping(value = com.rojmal.EndPoint.GET_BIRTH_REGISATION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public BirthRegister get(@PathVariable(value = "id") String id) {
		return birthRegisterService.get(id);
	}
	
	@RequestMapping(value = com.rojmal.EndPoint.GETALL_BIRTH_REGISATION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<BirthRegister> get() {
		return birthRegisterService.get();
	}
}