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

import com.rojmal.model.Party;
import com.rojmal.service.PartyService;

@RestController
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class PartyApiController {

	@Inject
	PartyService partyService;

	@RequestMapping(value = com.rojmal.EndPoint.INSERT_PARTY, method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Party insert(@RequestBody Party party) {
		return partyService.insert(party);
	}
}
