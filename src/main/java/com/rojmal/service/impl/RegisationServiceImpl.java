package com.rojmal.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Regisation;
import com.rojmal.repository.RegisationDao;
import com.rojmal.service.RegisationService;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class RegisationServiceImpl implements RegisationService {
	
	
	private Logger logger = LoggerFactory.getLogger(RegisationServiceImpl.class);

	@Inject
	RegisationDao regisationDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public Regisation insert(Regisation regisation) {
		
		logger.debug("insert() :: api call");
		
		if (regisation == null) {
			throw new IllegalArgumentException("User is not login");
		}
		if (StringUtils.isBlank(regisation.getUsername())
				|| StringUtils.isBlank(regisation.getPassword())) {
			throw new IllegalArgumentException(
					"Code :0001 :: UserName and password is invalid");

		}
		
		regisation.setPassword(passwordEncoder.encode(regisation.getPassword()));

		if (regisation.getBaltype() == null
				|| StringUtils.isBlank(regisation.getBaltype().toString())) {
			throw new IllegalArgumentException(
					"Code :0002 :: Balance Tyoe is must be specify");
		}
		Regisation reg = regisationDao.save(regisation);

		return reg;
	}
@Override
public Regisation get(String username) {
	return regisationDao.findByusername(username);
}
}
