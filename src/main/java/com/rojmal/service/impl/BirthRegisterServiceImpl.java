package com.rojmal.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.AuthorizationServiceException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.BirthRegister;
import com.rojmal.model.Regisation;
import com.rojmal.repository.BirthRegisterRepogitory;
import com.rojmal.service.BirthRegisterService;
import com.rojmal.service.RegisationService;
import com.rojmal.util.BeanMapper;

@Service
@Transactional(propagation = Propagation.MANDATORY)
public class BirthRegisterServiceImpl implements BirthRegisterService {

	@Inject
	RegisationService regisationService;
	@Inject
	BirthRegisterRepogitory birthRegisterRepogitory;

	@Inject
	BeanMapper beanMapper;

	@Override
	public BirthRegister insert(BirthRegister birthRegister) {
		// check Request body Object null or not
		// this.isvalidRequest(birthRegister);
		// check request
		// this.isValidBody(birthRegister);

		// get login user id
		Regisation regisation = regisationService.currentUser();
		// check user is login or not
		this.isValidRegisation(regisation);
		birthRegister.setRegisation(regisation);
		BirthRegister ber = birthRegisterRepogitory.save(birthRegister);
		return beanMapper.map(ber, BirthRegister.class, "birthfrom-1");
	}

	private void isValidRegisation(Regisation regisation) {

		if (regisation == null || StringUtils.isBlank(regisation.getId())) {
			throw new AuthorizationServiceException("User is not login");

		}
	}

	private void isvalidRequest(BirthRegister birthRegister) {
		if (birthRegister == null) {
			throw new IllegalArgumentException(
					"BirthRegister Object is not found or null.");
		}
	}

	private void isValidBody(BirthRegister birthRegister) {
		if (StringUtils.isBlank(birthRegister.getFirstname())) {
			throw new IllegalArgumentException("FirstName Should not be null");
		}

		if (StringUtils.isBlank(birthRegister.getLastname())) {
			throw new IllegalArgumentException("LastName Should not be null");
		}

		if (StringUtils.isBlank(birthRegister.getSex())) {
			throw new IllegalArgumentException("SEX Should not be null");
		}

		if (birthRegister.getDateofbirth() == null) {
			throw new IllegalArgumentException("DateOfBirth Should not be null");
		}

		if (StringUtils.isBlank(birthRegister.getNameoffather())) {
			throw new IllegalArgumentException("Father Name Should not be null");
		}

		if (StringUtils.isBlank(birthRegister.getNameofmother())) {
			throw new IllegalArgumentException(
					"Name of mmouther Should not be null");
		}

		if (StringUtils.isBlank(birthRegister.getBirthpalce())) {
			throw new IllegalArgumentException(
					"Birth place  Should not be null");
		}

		if (StringUtils.isBlank(birthRegister.getParmanentaddress())) {
			throw new IllegalArgumentException("FirstName Should not be null");
		}

		if (birthRegister.getDateofregistration() == null) {
			throw new IllegalArgumentException(
					"Date of registration Should not be null");
		}

	}

	@Override
	public BirthRegister update(String id, BirthRegister birthRegister) {

		// check Request body Object null or not
		this.isvalidRequest(birthRegister);
		// check request
		this.isValidBody(birthRegister);

		this.isValidId(id);

		BirthRegister birth = birthRegisterRepogitory.findOne(id);
		this.isvalidRequest(birth);

		// get login user id
		Regisation regisation = regisationService.currentUser();
		// check user is login or not
		this.isValidRegisation(regisation);
		birthRegister.setRegisation(regisation);
		BirthRegister ber = birthRegisterRepogitory.save(birthRegister);
		return ber;
	}

	@Override
	public void delete(String id) {
		// check id is null or not
		this.isValidId(id);
		// check give id is exist in data base or not.
		BirthRegister birthRegister = birthRegisterRepogitory.findOne(id);

		this.isvalidRequest(birthRegister);

		birthRegisterRepogitory.delete(birthRegister);

	}

	@Override
	public BirthRegister get(String id) {
		// check id is null or not
		this.isValidId(id);

		BirthRegister birthRegister = birthRegisterRepogitory.findOne(id);

		this.isvalidRequest(birthRegister);

		return birthRegister;
	}

	private void isValidId(String id) {
		if (StringUtils.isBlank(id)) {
			throw new IllegalArgumentException("id should not be null");
		}
	}

	@Override
	public List<BirthRegister> get() {

		// get login user id
		Regisation regisation = regisationService.currentUser();
		// check user is login or not
		this.isValidRegisation(regisation);

		return beanMapper.mapCollection(regisation.getBirthRegister(),
				BirthRegister.class, "birthfrom-1");

	}

}
