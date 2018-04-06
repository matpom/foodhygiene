package com.mattpomorski.mattsfoodhygiene.domain.exceptions;

public class LocalAuthoritiesException extends RuntimeException {

  public LocalAuthoritiesException(String message, Throwable cause) {
    super(message, cause);
  }

  public LocalAuthoritiesException(String message) {
    super(message);
  }
}
