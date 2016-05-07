package com.rojmal.service;

import org.springframework.stereotype.Service;

import com.rojmal.model.Bank;

@Service
public interface BankService {
	public Bank insert(Bank bank);

}
