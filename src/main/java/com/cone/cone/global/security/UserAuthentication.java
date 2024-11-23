package com.cone.cone.global.security;

import java.util.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.*;

public class UserAuthentication extends UsernamePasswordAuthenticationToken {

  public UserAuthentication(
      Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
    super(principal, credentials, authorities);
  }
}
