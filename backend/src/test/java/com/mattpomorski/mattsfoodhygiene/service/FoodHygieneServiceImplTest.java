package com.mattpomorski.mattsfoodhygiene.service;

import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_ESTABLISHMENTS;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_LOCAL_AUTHORITIES;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SAMPLE_LOCAL_AUTHORITY_ID;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SIMPLE_ESTABLISHMENTS;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SIMPLE_ESTABLISHMENTS_PROFILE;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SIMPLE_PROFILE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyCollection;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.mattpomorski.mattsfoodhygiene.client.FoodHygieneSupplier;
import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.EstablishmentsProfileException;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.LocalAuthoritiesException;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfile;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Rating;
import com.mattpomorski.mattsfoodhygiene.service.profile.ProfileResolver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FoodHygieneServiceImplTest {

  @Mock
  private FoodHygieneSupplier foodHygieneSupplierMock;

  @Mock
  private ProfileResolver<Establishment, Rating> profileResolverMock;

  @InjectMocks
  private FoodHygieneServiceImpl foodHygieneService;

  @Test
  public void shouldReturnDataFromSupplier() {
    //given
    when(foodHygieneSupplierMock.getLocalAuthorities()).thenReturn(SAMPLE_LOCAL_AUTHORITIES);
    //when
    List<LocalAuthority> actualLocalAuthorities = foodHygieneService.getLocalAuthorities();
    //then
    assertThat(actualLocalAuthorities, is(SAMPLE_LOCAL_AUTHORITIES));
    verify(foodHygieneSupplierMock).getLocalAuthorities();
  }

  @Test(expected = LocalAuthoritiesException.class)
  public void shouldThrowLocalAuthoritiesFetchException() {
    //given
    when(foodHygieneSupplierMock.getLocalAuthorities()).thenThrow(RuntimeException.class);
    //when
    foodHygieneService.getLocalAuthorities();
  }

  @Test
  public void shouldGetEstablishmentsForGivenLocalAuthorityId() {
    //given
    when(foodHygieneSupplierMock.getEstablishments(anyLong())).thenReturn(Collections.emptyList());
    //when
    foodHygieneService.getEstablishmentsProfile(SAMPLE_LOCAL_AUTHORITY_ID);
    //then
    verify(foodHygieneSupplierMock).getEstablishments(SAMPLE_LOCAL_AUTHORITY_ID);
  }

  @Test
  public void shouldUseProfileResolverToGetEstablishmentsProfile() {
    //given
    when(foodHygieneSupplierMock.getEstablishments(anyLong())).thenReturn(SAMPLE_ESTABLISHMENTS);
    //when
    foodHygieneService.getEstablishmentsProfile(SAMPLE_LOCAL_AUTHORITY_ID);
    //then
    verify(profileResolverMock).resolveRatingProfile(SAMPLE_ESTABLISHMENTS);
  }

  @Test
  public void shouldReturnSortedEstablishmentsProfile() {
    //given
    when(foodHygieneSupplierMock.getEstablishments(anyLong()))
        .thenReturn(SIMPLE_ESTABLISHMENTS);
    when(profileResolverMock.resolveRatingProfile(anyCollection()))
        .thenReturn(SIMPLE_PROFILE);
    //when
    EstablishmentsProfile actualEstablishmentsProfile =
        foodHygieneService.getEstablishmentsProfile(SAMPLE_LOCAL_AUTHORITY_ID);
    //then
    assertThat(actualEstablishmentsProfile, is(SIMPLE_ESTABLISHMENTS_PROFILE));
  }

  @Test(expected = EstablishmentsProfileException.class)
  public void shouldThrowEstablishmentsProfileExceptionWhenUnableToGetEstablishments() {
    //given
    when(foodHygieneSupplierMock.getEstablishments(anyLong())).thenThrow(RuntimeException.class);
    //when
    foodHygieneService.getEstablishmentsProfile(SAMPLE_LOCAL_AUTHORITY_ID);
  }

  @Test(expected = EstablishmentsProfileException.class)
  public void shouldThrowEstablishmentsProfileExceptionWhenResolvingProfileFails() {
    //given
    when(foodHygieneSupplierMock.getEstablishments(anyLong())).thenReturn(SAMPLE_ESTABLISHMENTS);
    when(profileResolverMock.resolveRatingProfile(any())).thenThrow(RuntimeException.class);
    //when
    foodHygieneService.getEstablishmentsProfile(SAMPLE_LOCAL_AUTHORITY_ID);
  }

}