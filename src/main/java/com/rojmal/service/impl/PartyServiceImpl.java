package com.rojmal.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Party;
import com.rojmal.repository.BankDao;
import com.rojmal.repository.PartyDao;
import com.rojmal.service.PartyService;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class PartyServiceImpl implements PartyService {

	@Inject
	PartyDao partyDao;

	@Override
	public Party insert(Party party) {
		if (party == null) {
			throw new IllegalArgumentException(
					"Request body should not be null");

		}
		if (StringUtils.isBlank(party.getName())) {
			throw new IllegalArgumentException("Bank name should not be null");
		}
		party = partyDao.save(party);
		return party;
	}

}
