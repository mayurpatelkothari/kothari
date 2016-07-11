package com.rojmal.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.rojmal.model.BirthRegister;

@Service
public interface BirthRegisterService {
	public BirthRegister insert(BirthRegister birthRegister);

	public BirthRegister update(String id, BirthRegister birthRegister);

	public void delete(String id);

	public BirthRegister get(String id);
	
	public List<BirthRegister> get();
}
