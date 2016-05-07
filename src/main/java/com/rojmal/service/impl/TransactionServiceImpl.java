package com.rojmal.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Transaction;
import com.rojmal.service.TransactionService;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class TransactionServiceImpl implements TransactionService {

	@Override
	public void insert(Transaction transaction) {

		if (transaction == null) {
			throw new IllegalArgumentException(
					"request body should not be null");

		}
		if (transaction.getAmount() == null) {
			throw new IllegalArgumentException("Amount should not be nulls");

		}
	}

}
