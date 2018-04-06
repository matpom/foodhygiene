package com.mattpomorski.mattsfoodhygiene.controllers.errors;

import java.util.Objects;

public class ErrorResponse {

  private final int code;
  private final String description;

  public ErrorResponse(int code, String description) {
    this.code = code;
    this.description = description;
  }

  public int getCode() {
    return code;
  }

  public String getDescription() {
    return description;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    ErrorResponse that = (ErrorResponse) o;
    return code == that.code &&
        Objects.equals(description, that.description);
  }

  @Override
  public int hashCode() {
    return Objects.hash(code, description);
  }

  @Override
  public String toString() {
    return "ErrorResponse{" +
        "code=" + code +
        ", description='" + description + '\'' +
        '}';
  }
}
