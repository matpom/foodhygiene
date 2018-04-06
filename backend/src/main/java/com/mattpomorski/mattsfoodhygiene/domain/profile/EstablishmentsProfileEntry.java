package com.mattpomorski.mattsfoodhygiene.domain.profile;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class EstablishmentsProfileEntry {

  @JsonIgnore
  private final Rating rating;
  @JsonIgnore
  private final Percentage percentage;

  public EstablishmentsProfileEntry(Rating rating, Percentage percentage) {
    this.rating = rating;
    this.percentage = percentage;
  }

  public Rating getRating() {
    return rating;
  }

  public Percentage getPercentage() {
    return percentage;
  }

  @JsonProperty("rating")
  public String getRatingName() {
    return rating.getName();
  }

  @JsonProperty("percentage")
  public Integer getPercentageRoundedValue() {
    return percentage.getRoundedValue();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EstablishmentsProfileEntry that = (EstablishmentsProfileEntry) o;
    return Objects.equals(rating, that.rating) &&
        Objects.equals(percentage, that.percentage);
  }

  @Override
  public int hashCode() {
    return Objects.hash(rating, percentage);
  }

  @Override
  public String toString() {
    return "EstablishmentProfileEntry{" +
        "rating=" + rating +
        ", percentage=" + percentage +
        '}';
  }
}