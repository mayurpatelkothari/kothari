package com.rojmal.controller;

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

import com.rojmal.model.Regisation;
import com.rojmal.model.Transaction;
import com.rojmal.service.RegisationService;
import com.rojmal.service.TransactionService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class TransactionApiController {

	@Inject
	RegisationService regisationService;

	@Inject
	TransactionService transactionService;

	@RequestMapping(value = com.rojmal.EndPoint.INSERT_TRANSACTION, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Regisation insert(@RequestBody Regisation regisation) {
		return regisationService.insert(regisation);
	}

	@RequestMapping(value = com.rojmal.EndPoint.UPDATE_TRANSACTION, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Transaction update(@RequestBody Transaction transaction,
			@PathVariable(value = "regisationId") String regisationId,
			@PathVariable(value = "id") String id) {
		return transactionService.update(transaction, regisationId, id);
	}

	@RequestMapping(value = com.rojmal.EndPoint.DELETE_TRANSACTION, method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void delete(
			@PathVariable(value = "regisationId") String regisationId,
			@PathVariable(value = "id") String id) {
		transactionService.delete(regisationId, id);
	}
	@RequestMapping(value = com.rojmal.EndPoint.GET_TRANSACTION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void get(
			@PathVariable(value = "regisationId") String regisationId,
			@PathVariable(value = "id") String id) {
		transactionService.get(regisationId, id);
	}
	
	@RequestMapping(value = com.rojmal.EndPoint.GETS_TRANSACTION, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void getList(
			@PathVariable(value = "regisationId") String regisationId,
			@PathVariable(value = "id") String id) {
		transactionService.get(regisationId, id);
	}

}
