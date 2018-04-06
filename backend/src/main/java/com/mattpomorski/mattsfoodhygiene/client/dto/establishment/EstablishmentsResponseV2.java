package com.mattpomorski.mattsfoodhygiene.client.dto.establishment;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class EstablishmentsResponseV2 {

  private List<EstablishmentV2> establishments;

  private EstablishmentsResponseV2() {
    //Required by Jackson
  }

  public EstablishmentsResponseV2(List<EstablishmentV2> establishments) {
    this.establishments = establishments;
  }

  public List<EstablishmentV2> getEstablishments() {
    return establishments;
  }

}
