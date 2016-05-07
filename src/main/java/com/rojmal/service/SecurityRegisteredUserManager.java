package com.rojmal.service;

import com.rojmal.model.Regisation;



public interface SecurityRegisteredUserManager {
  String getCurrentRegisteredUserId();
  Regisation get(String email);

}
