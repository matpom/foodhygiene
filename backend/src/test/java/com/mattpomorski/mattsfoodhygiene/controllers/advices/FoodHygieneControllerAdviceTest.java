package com.mattpomorski.mattsfoodhygiene.controllers.advices;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.mattpomorski.mattsfoodhygiene.controllers.errors.ErrorCodes;
import com.mattpomorski.mattsfoodhygiene.controllers.errors.ErrorResponse;
import com.mattpomorski.mattsfoodhygiene.controllers.exceptions.IllegalRequestParameter;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.EstablishmentsProfileException;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.LocalAuthoritiesException;
import org.junit.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class FoodHygieneControllerAdviceTest {

  private final FoodHygieneControllerAdvice advice = new FoodHygieneControllerAdvice();

  @Test
  public void shouldReturnErrorResponseForLocalAuthoritiesException() {
    //given
    ResponseEntity<ErrorResponse> expectedResponse = new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.LOCAL_AUTHORITIES_FAILED,
            "Unable to get local authorities, please try again latter."),
        HttpStatus.SERVICE_UNAVAILABLE);
    //when
    ResponseEntity<ErrorResponse> actualResponse = advice.handleLocalAuthoritiesException(
        new LocalAuthoritiesException(""));
    //then
    assertThat(actualResponse, is(expectedResponse));
  }

  @Test
  public void shouldReturnErrorResponseForEstablishmentsProfileException() {
    //given
    ResponseEntity<ErrorResponse> expectedResponse = new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.ESTABLISHMENTS_PROFILE_FAILED,
            "Unable to get establishments profile, please try again latter."),
        HttpStatus.SERVICE_UNAVAILABLE);
    //when
    ResponseEntity<ErrorResponse> actualResponse = advice.handleEstablishmentsProfileException(
        new EstablishmentsProfileException(""));
    //then
    assertThat(actualResponse, is(expectedResponse));
  }

  @Test
  public void shouldReturnErrorResponseForIllegalRequestParameterException() {
    //given
    ResponseEntity<ErrorResponse> expectedResponse = new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.ILLEGAL_REQUEST_PARAMETER,
            "Bad parameter included in the request."),
        HttpStatus.BAD_REQUEST);
    //when
    ResponseEntity<ErrorResponse> actualResponse = advice.handleIllegalRequestParameter(
        new IllegalRequestParameter(""));
    //then
    assertThat(actualResponse, is(expectedResponse));
  }

  @Test
  public void shouldReturnGenericErrorResponseForUnknownException() {
    //given
    ResponseEntity<ErrorResponse> expectedResponse = new ResponseEntity<>(
        new ErrorResponse(
            ErrorCodes.UNKNOWN_ERROR,
            "Unknown error occurred, please try again latter."),
        HttpStatus.INTERNAL_SERVER_ERROR);
    //when
    ResponseEntity<ErrorResponse> actualResponse = advice.handleGenericError(
        new RuntimeException());
    //then
    assertThat(actualResponse, is(expectedResponse));
  }

}