package com.rojmal.security;

import com.rojmal.model.Regisation;

public interface SecurityRegisteredUserManager
{
    String getCurrentRegisteredUserId();

    Regisation get(String username);
}
