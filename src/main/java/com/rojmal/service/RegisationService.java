package com.rojmal.service;

import org.springframework.stereotype.Service;

import com.rojmal.model.Regisation;

@Service
public interface RegisationService {
	public Regisation insert(Regisation regisation);

	public Regisation update(Regisation regisation);

	public Regisation get(String username);

	public Regisation getById(String id);

	public Regisation currentUser();

}
