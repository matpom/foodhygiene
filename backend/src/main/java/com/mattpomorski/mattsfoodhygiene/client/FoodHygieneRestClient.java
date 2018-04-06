package com.mattpomorski.mattsfoodhygiene.client;

import com.mattpomorski.mattsfoodhygiene.client.dto.authority.AuthoritiesResponseV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.authority.AuthorityV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.establishment.EstablishmentV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.establishment.EstablishmentsResponseV2;
import com.mattpomorski.mattsfoodhygiene.client.exceptions.UnsuccessfulResponseException;
import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FoodHygieneRestClient implements FoodHygieneSupplier {

  private static final Logger logger = LoggerFactory.getLogger(FoodHygieneRestClient.class);

  static final String X_API_VERSION_HEADER_NAME = "x-api-version";
  static final String X_API_VERSION_HEADER_VALUE = "2";
  public static final String LOCAL_AUTHORITIES_RESOURCE = "/Authorities/basic";
  public static final String ESTABLISHMENTS_RESOURCE = "/Establishments";

  private static final HttpHeaders headers = new HttpHeaders();

  static {
    headers.set(X_API_VERSION_HEADER_NAME, X_API_VERSION_HEADER_VALUE);
  }

  private final String baseUrl;
  private final RestTemplate restTemplate;

  public FoodHygieneRestClient(@Value("${food.hygiene.api.baseUrl}") String baseUrl,
                               RestTemplate restTemplate) {
    this.baseUrl = baseUrl;
    this.restTemplate = restTemplate;
  }

  @Override
  public List<LocalAuthority> getLocalAuthorities() {
    List<AuthorityV2> authorities = fetchLocalAuthorities();
    return authorities.stream().map(authorityV2 ->
        new LocalAuthority(authorityV2.getId(), authorityV2.getName()))
        .collect(Collectors.toList());
  }

  @Override
  public List<Establishment> getEstablishments(long localAuthorityId) {
    List<EstablishmentV2> establishments = fetchEstablishments(localAuthorityId);
    return establishments.stream().map(establishmentV2 ->
        new Establishment(establishmentV2.getId(), establishmentV2.getRatingValue()))
        .collect(Collectors.toList());
  }

  private List<AuthorityV2> fetchLocalAuthorities() {
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<AuthoritiesResponseV2> response;
    response = restTemplate.exchange(baseUrl + LOCAL_AUTHORITIES_RESOURCE,
        HttpMethod.GET, entity, AuthoritiesResponseV2.class);
    handleUnsuccessfulResponse(response);
    return response.getBody().getAuthorities();
  }

  private List<EstablishmentV2> fetchEstablishments(long localAuthorityId) {
    HttpEntity<String> entity = new HttpEntity<>(headers);
    ResponseEntity<EstablishmentsResponseV2> response;
    UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(baseUrl + ESTABLISHMENTS_RESOURCE)
        .queryParam("localAuthorityId", localAuthorityId)
        .queryParam("pageSize", 0);
    response = restTemplate.exchange(builder.toUriString(),
        HttpMethod.GET, entity, EstablishmentsResponseV2.class);
    handleUnsuccessfulResponse(response);
    return response.getBody().getEstablishments();
  }

  private void handleUnsuccessfulResponse(ResponseEntity response) {
    if (!response.getStatusCode().is2xxSuccessful() || response.getBody() == null) {
      logger.warn("Unsuccessful response from external service: {}", response);
      throw new UnsuccessfulResponseException();
    }
  }
}
