package com.rojmal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Bank;
import com.rojmal.model.Party;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface PartyDao extends JpaRepository<Party, String>,
		JpaSpecificationExecutor<String> {
}
