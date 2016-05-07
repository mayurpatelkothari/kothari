package com.rojmal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Transaction;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface TransactionDao extends JpaRepository<Transaction, String>,
		JpaSpecificationExecutor<String> {
}
