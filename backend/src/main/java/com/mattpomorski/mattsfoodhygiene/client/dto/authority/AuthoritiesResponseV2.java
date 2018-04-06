package com.mattpomorski.mattsfoodhygiene.client.dto.authority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthoritiesResponseV2 {

  private List<AuthorityV2> authorities;

  private AuthoritiesResponseV2() {
    //Required by Jackson
  }

  public AuthoritiesResponseV2(List<AuthorityV2> authorities) {
    this.authorities = authorities;
  }

  public List<AuthorityV2> getAuthorities() {
    return authorities;
  }

}
