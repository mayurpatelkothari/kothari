package com.rojmal.security;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import com.rojmal.model.Regisation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rojmal.repository.RegisationDao;

@Service
@Transactional
public class RegisteredUserDetailsServiceImpl implements UserDetailsService {
  private static Logger logger = LoggerFactory.getLogger(RegisteredUserDetailsServiceImpl.class);

  @Inject
  private  RegisationDao registrationRepository;

  public RegisteredUserDetailsServiceImpl() {
    logger.debug("initialized");
  }

  public static List<GrantedAuthority> getRoleBasedAuthorities(Regisation registration) {
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
    authorities.add(new SimpleGrantedAuthority(registration.getRole().getAuthority()));

    /*
     * for (Registration.Role role : registration.getRoles()) { authorities.add(new
     * SimpleGrantedAuthority(role.getAuthority())); }
     */
    return authorities;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
    	Regisation registration = registrationRepository.findByUsername(username);

      if (registration == null) {
        throw new RuntimeException("No registration found");
      }

      SecurityUser user = new SecurityUser(registration.getUsername(), registration.getPassword(),
          getRoleBasedAuthorities(registration));

      user.setId(registration.getId());

      return user;
    } catch (Exception e) {
      logger.error("loadUserByUsername:: failed", e);
    }

    throw new UsernameNotFoundException("Failed to find username in database: " + username);

  }

}
