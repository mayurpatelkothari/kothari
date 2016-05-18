package com.rojmal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Regisation;

@Repository
@Transactional(propagation = Propagation.MANDATORY)
public interface RegisationDao extends JpaRepository<Regisation, String>,
		JpaSpecificationExecutor<String> {

	Regisation findByusername(String username);

}
