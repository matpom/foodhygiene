package com.mattpomorski.mattsfoodhygiene.client.dto.authority;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class AuthorityV2 {

  @JsonProperty("LocalAuthorityId")
  private long id;

  @JsonProperty("Name")
  private String name;

  private AuthorityV2() {
    //Required by Jackson
  }

  public AuthorityV2(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

}
