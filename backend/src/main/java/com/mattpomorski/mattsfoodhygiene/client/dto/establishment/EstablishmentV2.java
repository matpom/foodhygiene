package com.mattpomorski.mattsfoodhygiene.client.dto.establishment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EstablishmentV2 {

  @JsonProperty("FHRSID")
  private long id;

  @JsonProperty("RatingValue")
  private String ratingValue;

  private EstablishmentV2() {
    //Required by Jackson
  }

  public EstablishmentV2(long id, String ratingValue) {
    this.id = id;
    this.ratingValue = ratingValue;
  }

  public long getId() {
    return id;
  }

  public String getRatingValue() {
    return ratingValue;
  }

}
