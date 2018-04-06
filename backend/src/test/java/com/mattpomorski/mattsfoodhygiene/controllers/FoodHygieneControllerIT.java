package com.mattpomorski.mattsfoodhygiene.controllers;

import static com.mattpomorski.mattsfoodhygiene.client.FoodHygieneRestClient.ESTABLISHMENTS_RESOURCE;
import static com.mattpomorski.mattsfoodhygiene.client.FoodHygieneRestClient.LOCAL_AUTHORITIES_RESOURCE;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_LOCAL_AUTHORITY_ID;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withBadRequest;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mattpomorski.mattsfoodhygiene.utils.ResourceReader;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class FoodHygieneControllerIT {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${food.hygiene.api.baseUrl}")
  private String baseUri;

  private MockRestServiceServer mockServer;

  @Before
  public void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  public void shouldReturnLocalAuthoritiesJson() throws Exception {
    //given
    localAuthoritiesRespondsOK();
    //when
    mockMvc.perform(get("/localAuthorities"))
        //then
        .andExpect(status().isOk())
        .andExpect(content().json(
            ResourceReader.readResourceAsString(
                "internal_responses/local_authorities.json"), true)
        );
  }

  @Test
  public void shouldReturnErrorResponseWhenUnableToGetLocalAuthorities() throws Exception {
    //given
    localAuthoritiesRespondsWithError();
    //when
    mockMvc.perform(get("/localAuthorities"))
        //then
        .andDo(print())
        .andExpect(status().isServiceUnavailable())
        .andExpect(content().json(
            ResourceReader.readResourceAsString(
                "internal_responses/errors/local_authorities_error.json"), true)
        );
  }

  @Test
  public void shouldReturnEstablishmentsProfileJson() throws Exception {
    //given
    establishmentsRespondsOK();
    //when
    mockMvc.perform(get("/establishmentsProfile?localAuthorityId=" + SAMPLE_LOCAL_AUTHORITY_ID))
        //then
        .andExpect(status().isOk())
        .andExpect(content().json(
            ResourceReader.readResourceAsString(
                "internal_responses/establishments_profile.json"), true)
        );
  }

  @Test
  public void shouldReturnErrorResponseWhenUnableToGetEstablishments() throws Exception {
    //given
    establishmentsRespondsWithError();
    //when
    mockMvc.perform(get("/establishmentsProfile?localAuthorityId=" + SAMPLE_LOCAL_AUTHORITY_ID))
        //then
        .andDo(print())
        .andExpect(status().isServiceUnavailable())
        .andExpect(content().json(
            ResourceReader.readResourceAsString(
                "internal_responses/errors/establishments_error.json"), true)
        );
  }

  @Test
  public void shouldReturnIllegalParameterErrorResponse() throws Exception {
    //given
    establishmentsRespondsWithError();
    //when
    mockMvc.perform(get("/establishmentsProfile?localAuthorityId=abc"))
        //then
        .andDo(print())
        .andExpect(status().isBadRequest())
        .andExpect(content().json(
            ResourceReader.readResourceAsString(
                "internal_responses/errors/illegal_parameter_error.json"), true)
        );
  }

  private void localAuthoritiesRespondsWithError() {
    mockServer.expect(requestTo(baseUri + LOCAL_AUTHORITIES_RESOURCE))
        .andRespond(withBadRequest());
  }

  private void localAuthoritiesRespondsOK() {
    mockServer.expect(requestTo(baseUri + LOCAL_AUTHORITIES_RESOURCE))
        .andRespond(withSuccess(
            ResourceReader.readResourceAsString("external_responses/authorities_sample.json"),
            MediaType.APPLICATION_JSON_UTF8));
  }

  private void establishmentsRespondsOK() {
    mockServer.expect(requestTo(baseUri + ESTABLISHMENTS_RESOURCE +
        "?localAuthorityId=" + SAMPLE_LOCAL_AUTHORITY_ID + "&pageSize=0"))
        .andRespond(withSuccess(
            ResourceReader.readResourceAsString("external_responses/establishments_sample.json"),
            MediaType.APPLICATION_JSON_UTF8));
  }

  private void establishmentsRespondsWithError() {
    mockServer.expect(requestTo(baseUri + ESTABLISHMENTS_RESOURCE +
        "?localAuthorityId=" + SAMPLE_LOCAL_AUTHORITY_ID + "&pageSize=0"))
        .andRespond(withBadRequest());
  }

}