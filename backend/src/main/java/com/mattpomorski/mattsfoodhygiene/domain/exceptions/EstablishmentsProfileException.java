package com.mattpomorski.mattsfoodhygiene.domain.exceptions;

public class EstablishmentsProfileException extends RuntimeException {

  public EstablishmentsProfileException(String message, Throwable cause) {
    super(message, cause);
  }

  public EstablishmentsProfileException(String message) {
    super(message);
  }
}
