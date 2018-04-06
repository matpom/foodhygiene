package com.mattpomorski.mattsfoodhygiene.controllers;

import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_LOCAL_AUTHORITIES;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_LOCAL_AUTHORITY_ID;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SIMPLE_ESTABLISHMENTS_PROFILE;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.mattpomorski.mattsfoodhygiene.service.FoodHygieneServiceImpl;
import com.mattpomorski.mattsfoodhygiene.utils.ResourceReader;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(FoodHygieneController.class)
public class FoodHygieneControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private FoodHygieneServiceImpl foodHygieneServiceMock;

  @Test
  public void shouldReturnLocalAuthoritiesJson() throws Exception {
    //given
    when(foodHygieneServiceMock.getLocalAuthorities()).thenReturn(SAMPLE_LOCAL_AUTHORITIES);
    //when
    mockMvc.perform(get("/localAuthorities"))
        //then
        .andExpect(status().isOk())
        .andExpect(content().json(
            ResourceReader.readResourceAsString("internal_responses/local_authorities.json"),
            true)
        );
  }

  @Test
  public void shouldReturnEstablishmentsProfileJson() throws Exception {
    //given
    when(foodHygieneServiceMock.getEstablishmentsProfile(SAMPLE_LOCAL_AUTHORITY_ID))
        .thenReturn(SIMPLE_ESTABLISHMENTS_PROFILE);
    //when
    mockMvc.perform(get("/establishmentsProfile?localAuthorityId=" + SAMPLE_LOCAL_AUTHORITY_ID))
        //then
        .andExpect(status().isOk())
        .andExpect(content().json(
            ResourceReader.readResourceAsString("internal_responses/simple_establishments_profile.json"),
            true)
        );
  }

}