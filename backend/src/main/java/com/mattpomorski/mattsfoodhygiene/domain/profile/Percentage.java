package com.mattpomorski.mattsfoodhygiene.domain.profile;

import java.util.Objects;

public class Percentage {

  private final int roundedValue;

  public Percentage(int roundedValue) {
    this.roundedValue = roundedValue;
  }

  public int getRoundedValue() {
    return roundedValue;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Percentage that = (Percentage) o;
    return roundedValue == that.roundedValue;
  }

  @Override
  public int hashCode() {

    return Objects.hash(roundedValue);
  }

  @Override
  public String toString() {
    return "Percentage{" +
        "roundedValue=" + roundedValue +
        '}';
  }
}
