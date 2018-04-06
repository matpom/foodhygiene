package com.mattpomorski.mattsfoodhygiene.controllers.exceptions;

public class IllegalRequestParameter extends RuntimeException {
  public IllegalRequestParameter(String message) {
    super(message);
  }
}
