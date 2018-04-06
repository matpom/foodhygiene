package com.mattpomorski.mattsfoodhygiene.domain;


import java.util.Objects;

public class LocalAuthority {

  private final long id;
  private final String name;

  public LocalAuthority(long id, String name) {
    this.id = id;
    this.name = name;
  }

  public long getId() {
    return id;
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
    LocalAuthority that = (LocalAuthority) o;
    return id == that.id &&
        Objects.equals(name, that.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    return "LocalAuthority{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
