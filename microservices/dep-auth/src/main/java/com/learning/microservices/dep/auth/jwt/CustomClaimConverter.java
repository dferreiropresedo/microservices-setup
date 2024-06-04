package com.learning.microservices.dep.auth.jwt;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

@Slf4j
public class CustomClaimConverter implements Converter<Jwt, AbstractAuthenticationToken> {

  private static final String CUSTOM_REALM_PROPERTY = "realm_access";
  private static final String ROLE_PROPERTY = "roles";

  @Override
  public AbstractAuthenticationToken convert(Jwt source) {
    Collection<GrantedAuthority> authorities = new HashSet<>();
    authorities.addAll(getScopes(source));
    authorities.addAll(getRoles(source));
    return new JwtAuthenticationToken(source, authorities);
  }

  private Set<GrantedAuthority> getScopes(Jwt source) {
    log.debug("Retrieving the user scope from the JWT default fields");
    return new HashSet<>(new JwtGrantedAuthoritiesConverter().convert(source));
  }

  @SuppressWarnings("unchecked")
  private Set<GrantedAuthority> getRoles(Jwt source) {
    log.debug("Retrieving the user roles from the JWT 'role' field");
    Set<GrantedAuthority> roles = new HashSet<>();

    try {
      List<String> jwtRoles = (ArrayList<String>) Optional.ofNullable(
              source.getClaimAsMap(CUSTOM_REALM_PROPERTY))
          .orElse(new HashMap<>()).getOrDefault(ROLE_PROPERTY, new ArrayList<>());

      for (String jwtRole : jwtRoles) {
        roles.add(new SimpleGrantedAuthority("ROLE_" + jwtRole.toUpperCase(Locale.ENGLISH)));
      }
    } catch (ClassCastException e) {
      log.warn("The token {} did not have any recognizable roles.", source.getId());
    }

    return roles;
  }


}
