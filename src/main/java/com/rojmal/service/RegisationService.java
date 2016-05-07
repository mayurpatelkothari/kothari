package com.rojmal.service;

import org.springframework.stereotype.Service;

import com.rojmal.model.Regisation;

@Service
public interface RegisationService {
	
	public Regisation insert(Regisation regisation);

}
