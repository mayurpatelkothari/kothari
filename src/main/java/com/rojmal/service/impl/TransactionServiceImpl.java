package com.rojmal.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Regisation;
import com.rojmal.model.Transaction;
import com.rojmal.repository.RegisationDao;
import com.rojmal.repository.TransactionDao;
import com.rojmal.service.RegisationService;
import com.rojmal.service.TransactionService;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class TransactionServiceImpl implements TransactionService {

	@Inject
	private TransactionDao transactionDao;
	@Inject
	private RegisationService regisationService;

	@Override
	public Transaction insert(Transaction transaction) {

		if (transaction == null) {
			throw new IllegalArgumentException(
					"request body should not be null");

		}
		if (transaction.getAmount() == null) {
			throw new IllegalArgumentException("Amount should not be nulls");

		}
		return transactionDao.save(transaction);
	}

	@Override
	public Transaction update(Transaction transaction, String regisationId,
			String id) {
		this.validateId(id);
		if (StringUtils.isBlank(regisationId)) {
			throw new IllegalArgumentException(
					"path Perameter should not be null");
		}
		Regisation regisation = regisationService.getById(id);
		if (regisation == null) {
			throw new IllegalArgumentException("Regisation Entity not found");
		}
		Transaction tra = this.get(id);
		if (tra == null) {
			throw new IllegalArgumentException("Transaction Entity not found");
		}
		transaction.setRegisation(regisation);
		return transactionDao.save(transaction);
	}

	@Override
	public Transaction get(String id) {
		return transactionDao.getOne(id);

	}

	@Override
	public void delete(String regisationId, String id) {
		this.validateId(id);
		if (StringUtils.isBlank(regisationId)) {
			throw new IllegalArgumentException(
					"path Perameter should not be null");
		}
		transactionDao.delete(id);
	}

	private void validateId(String id) {
		if (StringUtils.isBlank(id)) {
			throw new IllegalArgumentException("Invalid Id");
		}

	}

	@Override
	public Transaction get(String regisationId, String id) {
		this.validateId(id);
		if (StringUtils.isBlank(regisationId)) {
			throw new IllegalArgumentException(
					"path Perameter should not be null");
		}

		Regisation regisation = regisationService.getById(regisationId);
		if (StringUtils.equals(regisation.getId(), regisation.getId())) {
			throw new IllegalArgumentException(
					"user is not owner of this transection");
		}

		return this.get(id);
	}

	@Override
	public List<Transaction> gets(String regisationId, String id) {
		this.validateId(id);
		if (StringUtils.isBlank(regisationId)) {
			throw new IllegalArgumentException(
					"path Perameter should not be null");
		}

		Regisation regisation = regisationService.getById(regisationId);
		if (StringUtils.equals(regisation.getId(), regisation.getId())) {
			throw new IllegalArgumentException(
					"user is not owner of this transection");
		}

		return transactionDao.findAll();
	}
}
