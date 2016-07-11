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

import com.rojmal.model.BirthRegister;
import com.rojmal.model.Regisation;
import com.rojmal.model.Regisation.Role;
import com.rojmal.repository.RegisationDao;
import com.rojmal.security.SecurityRegisteredUserManager;
import com.rojmal.service.RegisationService;
import com.rojmal.util.BeanMapper;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class RegisationServiceImpl implements RegisationService {

	private Logger logger = LoggerFactory
			.getLogger(RegisationServiceImpl.class);

	@Inject
	RegisationDao regisationDao;
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Inject
	BeanMapper beanMapper;

	@Inject
	SecurityRegisteredUserManager securityRegisteredUserManager;

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
		regisation.setRole(Role.ROLE_USER);

		regisation
				.setPassword(passwordEncoder.encode(regisation.getPassword()));
		Regisation reg = regisationDao.save(regisation);

		return reg;
	}

	@Override
	public Regisation get(String username) {

		Regisation regisation = regisationDao.findByusername(username);
		if (regisation == null) {
			return null;
		}

		return beanMapper.map(regisation, Regisation.class, "regisation-1");

	}

	@Override
	public Regisation getById(String id) {
		// Regisation regi = regisationDao.findOne(id);
		// if (regi == null) {
		// throw new IllegalAccessError("User not login");
		// }
		return regisationDao.getOne(id);
	}

	@Override
	public Regisation currentUser() {

		String userId = securityRegisteredUserManager
				.getCurrentRegisteredUserId();

		Regisation regisation = regisationDao.findOne(userId);
		return regisation;
	}

	@Override
	public Regisation update(Regisation regisation) {
		Regisation regi = this.currentUser();
		if (regi == null) {
			throw new IllegalArgumentException("User is not login");
		}
		regisation.setId(regi.getId());

		return regisationDao.save(regisation);
	}

}
