package com.rojmal.security;

import com.rojmal.model.Regisation.Role;


/**
 *
 */
public interface SecurityServiceHelper {

  /**
   * This method is used to logged in using system user
   */
  void loginAsSystem();

  void loginAsUser(String username);

  void loginAsRole(String role);

  void loginAsRole(Role role);

  void logout();
}
