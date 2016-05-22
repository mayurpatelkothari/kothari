package com.rojmal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rojmal.model.Transaction;

@Service
public interface TransactionService {
	public Transaction insert(Transaction transaction);

	public Transaction update(Transaction transaction, String regisationId,
			String id);

	public Transaction get(String id);

	public void delete(String regisationId, String id);
	
	public Transaction get(String regisationId, String id);
	public List<Transaction> gets(String regisationId, String id);

}
