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

import com.rojmal.model.Bank;
import com.rojmal.service.BankService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class BankApiController {
	
	@Inject
	BankService bankService;
	
	@RequestMapping(value = com.rojmal.EndPoint.INSERT_BANK, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
   @ResponseBody
	public Bank insert(@RequestBody Bank bank) {
        return bankService.insert(bank);
    }
}
