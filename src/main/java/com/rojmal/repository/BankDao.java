package com.rojmal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Bank;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface BankDao extends JpaRepository<Bank, String>,
		JpaSpecificationExecutor<String> {
}
