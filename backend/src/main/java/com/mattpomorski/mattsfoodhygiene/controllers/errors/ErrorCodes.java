package com.mattpomorski.mattsfoodhygiene.controllers.errors;

public final class ErrorCodes {

  private ErrorCodes() {
    // restrict instantiation
  }

  public static final int LOCAL_AUTHORITIES_FAILED = 1000;
  public static final int ESTABLISHMENTS_PROFILE_FAILED = 1001;

  public static final int ILLEGAL_REQUEST_PARAMETER = 4001;

  public static final int UNKNOWN_ERROR = 5001;

}
