package com.rojmal.service;

import org.springframework.stereotype.Service;

import com.rojmal.model.Transaction;

@Service
public interface TransactionService {
	public void insert(Transaction transaction);
}
