package com.rojmal.service;

import org.springframework.stereotype.Service;

import com.rojmal.model.Party;

@Service
public interface PartyService {
	public Party insert(Party party);

}
