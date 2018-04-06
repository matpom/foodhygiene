package com.mattpomorski.mattsfoodhygiene.service.profile;

import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SIMPLE_ESTABLISHMENTS;
import static com.mattpomorski.mattsfoodhygiene.utils.TestData.SIMPLE_PROFILE;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.google.common.collect.ImmutableList;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Rating;
import com.mattpomorski.mattsfoodhygiene.service.profile.percentage.LeastErrorPercentageCalculator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;
import java.util.Map;

@RunWith(MockitoJUnitRunner.class)
public class EstablishmentRatingProfileResolverTest {

  private static final ImmutableList<Percentage> EXPECTED_PERCENTAGE = ImmutableList.of(
      new Percentage(25),
      new Percentage(50),
      new Percentage(25)
  );

  @Mock
  private LeastErrorPercentageCalculator percentageCalculator;

  @InjectMocks
  private EstablishmentRatingProfileResolver profileResolver;

  @Before
  public void init() {
    when(percentageCalculator.calculatePercentage(anyList()))
        .thenReturn(EXPECTED_PERCENTAGE);
  }

  @Test
  public void shouldReturnEmptyMapWhenCalledWithEmptyCollection() {
    //when
    Map<Rating, Percentage> actualProfile =
        profileResolver.resolveRatingProfile(Collections.emptyList());
    //then
    assertThat(actualProfile, is(Collections.emptyMap()));
    verify(percentageCalculator, never()).calculatePercentage(anyList());
  }

  @Test
  public void shouldCallPercentageCalculatorWithAccumulatedCounts() {
    //given
    List<Long> expectedCounts = ImmutableList.of(1L, 2L, 1L);
    //when
    profileResolver.resolveRatingProfile(SIMPLE_ESTABLISHMENTS);
    //then
    verify(percentageCalculator).calculatePercentage(expectedCounts);
  }

  @Test
  public void shouldResolveSimpleProfile() {
    //when
    Map<Rating, Percentage> actualProfile =
        profileResolver.resolveRatingProfile(SIMPLE_ESTABLISHMENTS);
    //then
    assertThat(actualProfile, is(SIMPLE_PROFILE));
  }
}