package com.mattpomorski.mattsfoodhygiene.domain.profile;

import java.util.Objects;

public class Rating implements Comparable<Rating> {

  private final String name;

  public Rating(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Rating that = (Rating) o;
    return Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {

    return Objects.hash(name);
  }

  @Override
  public String toString() {
    return "Rating{" +
        "name='" + name + '\'' +
        '}';
  }

  @Override
  public int compareTo(Rating that) {
    boolean thisIsInteger = isIntegerValue(this.name);
    boolean thatIsInteger = isIntegerValue(that.name);
    if(thisIsInteger && thatIsInteger){
      return Integer.parseInt(that.name) - Integer.parseInt(this.name);
    }
    if(thisIsInteger){
      return -1;
    }
    if(thatIsInteger) {
      return 1;
    }
    return this.name.compareTo(that.name);
  }

  private boolean isIntegerValue(String string) {
    return string.matches("-?\\d+");
  }
}
