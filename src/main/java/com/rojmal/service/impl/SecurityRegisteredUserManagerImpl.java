package com.rojmal.service.impl;

import javax.inject.Inject;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Regisation;
import com.rojmal.repository.RegisationDao;
import com.rojmal.security.SecurityUser;
import com.rojmal.service.SecurityRegisteredUserManager;

@Service
@Transactional(propagation = Propagation.REQUIRED)
public class SecurityRegisteredUserManagerImpl implements SecurityRegisteredUserManager {
	
	@Inject
	RegisationDao regisationDao;
  @Override
  public String getCurrentRegisteredUserId() {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {
      Object principal = authentication.getPrincipal();
      if (principal instanceof SecurityUser) {
        SecurityUser user = (SecurityUser) principal;
        return user.getId();
      }
    }
    return null;
  }

@Override
public Regisation get(String userName) {
    return regisationDao.findByUsername(userName);
}
}
