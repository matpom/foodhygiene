package com.mattpomorski.mattsfoodhygiene.controllers.advices;

import com.mattpomorski.mattsfoodhygiene.controllers.FoodHygieneController;
import com.mattpomorski.mattsfoodhygiene.controllers.errors.ErrorCodes;
import com.mattpomorski.mattsfoodhygiene.controllers.errors.ErrorResponse;
import com.mattpomorski.mattsfoodhygiene.controllers.exceptions.IllegalRequestParameter;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.EstablishmentsProfileException;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.LocalAuthoritiesException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(assignableTypes = FoodHygieneController.class)
public class FoodHygieneControllerAdvice {

  private static final Logger logger = LoggerFactory.getLogger(FoodHygieneControllerAdvice.class);

  private static final String EXCEPTION_WAS_THROWN = "Exception was thrown: ";

  @ExceptionHandler(LocalAuthoritiesException.class)
  public ResponseEntity<ErrorResponse> handleLocalAuthoritiesException(Exception ex) {
    logger.error(EXCEPTION_WAS_THROWN, ex);
    return new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.LOCAL_AUTHORITIES_FAILED,
            "Unable to get local authorities, please try again latter."),
        HttpStatus.SERVICE_UNAVAILABLE);
  }

  @ExceptionHandler(EstablishmentsProfileException.class)
  public ResponseEntity<ErrorResponse> handleEstablishmentsProfileException(Exception ex) {
    logger.error(EXCEPTION_WAS_THROWN, ex);
    return new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.ESTABLISHMENTS_PROFILE_FAILED,
            "Unable to get establishments profile, please try again latter."),
        HttpStatus.SERVICE_UNAVAILABLE);
  }



  @ExceptionHandler(IllegalRequestParameter.class)
  public ResponseEntity<ErrorResponse> handleIllegalRequestParameter(Exception ex) {
    logger.error(EXCEPTION_WAS_THROWN, ex);
    return new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.ILLEGAL_REQUEST_PARAMETER,
            "Bad parameter included in the request."),
        HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResponse> handleGenericError(Exception ex) {
    logger.error("Unknown exception was thrown: ", ex);
    return new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.UNKNOWN_ERROR,
            "Unknown error occurred, please try again latter."),
        HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
