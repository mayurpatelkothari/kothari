package com.rojmal.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.Registration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.model.Regisation;
import com.rojmal.repository.RegisationDao;

@Component
@Transactional(propagation = Propagation.REQUIRES_NEW)
public class RegistrationDetail implements UserDetailsService {

	private static Logger logger = LoggerFactory.getLogger(RegistrationDetail.class);

	@Inject
	RegisationDao registrationDao;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		// get registration using given user name (email)
		Regisation registration = registrationDao.findByusername(username);
		SecurityUser securityUser = null;

		if (registration == null) {
			return null;
		}

		try {
			if (registration != null) {
				securityUser = new SecurityUser(registration.getUsername(), registration.getPassword(), true, true, true, true,
						getRoleBasedAuthorities(registration));
				
				securityUser.setId(registration.getId());
				return securityUser;
			}
		} catch (Exception e) {
			logger.error("loadUserByUsername:: failed", e);
		}
		return null;
	}

	public static List<GrantedAuthority> getRoleBasedAuthorities(Regisation registration) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(registration.getAuthority()));
		return authorities;
	}

}
