package com.mattpomorski.mattsfoodhygiene.domain;

import com.mattpomorski.mattsfoodhygiene.domain.profile.Rating;

import java.util.Objects;

public class Establishment {

  private final long id;
  private final Rating rating;

  public Establishment(long id, String rating) {
    this.id = id;
    this.rating = new Rating(rating);
  }

  public long getId() {
    return id;
  }

  public Rating getRating() {
    return rating;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Establishment that = (Establishment) o;
    return Objects.equals(id, that.id) &&
        Objects.equals(rating, that.rating);
  }

  @Override
  public int hashCode() {

    return Objects.hash(id, rating);
  }

  @Override
  public String toString() {
    return "Establishment{" +
        "id='" + id + '\'' +
        ", rating='" + rating + '\'' +
        '}';
  }
}
