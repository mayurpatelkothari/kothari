package com.rojmal.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Bank;
import com.rojmal.repository.BankDao;
import com.rojmal.service.BankService;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class BankServiceImpl implements BankService {

	@Inject
	BankDao bankDao;

	@Override
	public Bank insert(Bank bank) {
		if (bank == null) {
			throw new IllegalArgumentException(
					"Request body should not be null");

		}
		if (StringUtils.isBlank(bank.getName())) {
			throw new IllegalArgumentException("Bank name should not be null");
		}
		bank = bankDao.save(bank);
		return bank;
	}

}
