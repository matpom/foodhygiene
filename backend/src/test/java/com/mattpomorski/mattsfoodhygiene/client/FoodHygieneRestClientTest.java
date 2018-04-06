package com.mattpomorski.mattsfoodhygiene.client;

import static com.mattpomorski.mattsfoodhygiene.client.FoodHygieneRestClient.ESTABLISHMENTS_RESOURCE;
import static com.mattpomorski.mattsfoodhygiene.client.FoodHygieneRestClient.LOCAL_AUTHORITIES_RESOURCE;
import static com.mattpomorski.mattsfoodhygiene.client.FoodHygieneRestClient.X_API_VERSION_HEADER_NAME;
import static com.mattpomorski.mattsfoodhygiene.client.FoodHygieneRestClient.X_API_VERSION_HEADER_VALUE;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_AUTHORITIES_RESPONSE;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_ESTABLISHMENTS;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_ESTABLISHMENTS_RESPONSE;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_LOCAL_AUTHORITIES;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mattpomorski.mattsfoodhygiene.client.dto.authority.AuthoritiesResponseV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.establishment.EstablishmentsResponseV2;
import com.mattpomorski.mattsfoodhygiene.client.exceptions.UnsuccessfulResponseException;
import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

public class FoodHygieneRestClientTest {

  private static final String SAMPLE_BASE_URL = "http://some.domain:8080";
  private static final long SAMPLE_LOCAL_AUTHORITY_ID = 912L;

  private FoodHygieneRestClient foodHygieneRestClient;

  private RestTemplate restTemplateMock;

  @Before
  public void init() {
    restTemplateMock = mock(RestTemplate.class);
    foodHygieneRestClient = new FoodHygieneRestClient(SAMPLE_BASE_URL, restTemplateMock);
  }

  @Test
  public void shouldCallLocalAuthoritiesEndpointWithHeaders() {
    //given
    mockLocalAuthoritiesResponseOK();
    //when
    foodHygieneRestClient.getLocalAuthorities();
    //then
    verify(restTemplateMock).exchange(
        eq(SAMPLE_BASE_URL + LOCAL_AUTHORITIES_RESOURCE),
        eq(HttpMethod.GET),
        argThat(httpEntity ->
            Objects.requireNonNull(httpEntity.getHeaders().get(X_API_VERSION_HEADER_NAME))
                .contains(X_API_VERSION_HEADER_VALUE)
        ),
        eq(AuthoritiesResponseV2.class));
  }

  @Test(expected = UnsuccessfulResponseException.class)
  public void shouldThrowUnsuccessfulResponseExceptionWhenFetchingLocalAuthoritiesFails() {
    //given
    when(restTemplateMock.exchange(anyString(), any(), any(), eq(AuthoritiesResponseV2.class)))
        .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    //when
    foodHygieneRestClient.getLocalAuthorities();
  }

  @Test
  public void shouldReturnLocalAuthoritiesFetchedFromExternalApi() {
    //given
    mockLocalAuthoritiesResponseOK();
    //when
    List<LocalAuthority> actualLocalAuthorities = foodHygieneRestClient.getLocalAuthorities();
    //then
    assertThat(actualLocalAuthorities, is(SAMPLE_LOCAL_AUTHORITIES));
  }

  private void mockLocalAuthoritiesResponseOK() {
    when(restTemplateMock.exchange(anyString(), any(), any(), eq(AuthoritiesResponseV2.class)))
        .thenReturn(new ResponseEntity<>(SAMPLE_AUTHORITIES_RESPONSE, HttpStatus.OK));
  }

  @Test
  public void shouldCallEstablishmentsEndpointWithParametersAndHeaders() {
    //given
    mockEstablishmentsResponseOK();
    //when
    foodHygieneRestClient.getEstablishments(SAMPLE_LOCAL_AUTHORITY_ID);
    //then
    verify(restTemplateMock).exchange(
        eq(SAMPLE_BASE_URL + ESTABLISHMENTS_RESOURCE
            + "?localAuthorityId=" + SAMPLE_LOCAL_AUTHORITY_ID + "&pageSize=0"),
        eq(HttpMethod.GET),
        argThat(httpEntity ->
            Objects.requireNonNull(httpEntity.getHeaders().get(X_API_VERSION_HEADER_NAME))
                .contains(X_API_VERSION_HEADER_VALUE)
        ),
        eq(EstablishmentsResponseV2.class));
  }

  @Test(expected = UnsuccessfulResponseException.class)
  public void shouldThrowUnsuccessfulResponseExceptionWhenFetchingEstablishmentsFails() {
    //given
    when(restTemplateMock.exchange(anyString(), any(), any(), eq(EstablishmentsResponseV2.class)))
        .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));
    //when
    foodHygieneRestClient.getEstablishments(SAMPLE_LOCAL_AUTHORITY_ID);
  }

  @Test
  public void shouldReturnEstablishmentsFetchedFromExternalApi() {
    //given
    mockEstablishmentsResponseOK();
    //when
    List<Establishment> actualLocalAuthorities = foodHygieneRestClient.getEstablishments(SAMPLE_LOCAL_AUTHORITY_ID);
    //then
    assertThat(actualLocalAuthorities, is(SAMPLE_ESTABLISHMENTS));
  }

  private void mockEstablishmentsResponseOK() {
    when(restTemplateMock.exchange(anyString(), any(), any(), eq(EstablishmentsResponseV2.class)))
        .thenReturn(new ResponseEntity<>(SAMPLE_ESTABLISHMENTS_RESPONSE, HttpStatus.OK));
  }

}