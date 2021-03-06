package com.rojmal.service.impl;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Regisation;
import com.rojmal.repository.RegisationDao;
import com.rojmal.service.RegisationService;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class RegisationServiceImpl implements RegisationService {

	@Inject
	RegisationDao regisationDao;

	@Override
	public Regisation insert(Regisation regisation) {

		if (regisation == null) {
			throw new IllegalArgumentException("User is not login");
		}
		if (StringUtils.isBlank(regisation.getUsername())
				|| StringUtils.isBlank(regisation.getPassword())) {
			throw new IllegalArgumentException(
					"Code :0001 :: UserName and password is invalid");

		}

		if (regisation.getBaltype()== null
				|| StringUtils.isBlank(regisation.getBaltype().toString())) {
			throw new IllegalArgumentException(
					"Code :0002 :: Balance Tyoe is must be specify");
		}
		Regisation reg = regisationDao.save(regisation);

		return reg;
	}

}
