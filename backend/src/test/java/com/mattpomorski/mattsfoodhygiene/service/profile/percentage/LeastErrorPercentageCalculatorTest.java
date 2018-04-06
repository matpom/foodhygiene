package com.mattpomorski.mattsfoodhygiene.service.profile.percentage;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import com.google.common.collect.ImmutableList;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

public class LeastErrorPercentageCalculatorTest {

  private final PercentageCalculator percentageCalculator = new LeastErrorPercentageCalculator();

  @Test
  public void shouldReturnCalculatedPercentage() {
    List<Long> counts = ImmutableList.of(1L, 1L); //(0.5000, 0.5000)
    List<Integer> expectedRoundedValues = ImmutableList.of(50, 50);
    assertCalculatedPercentage(counts, expectedRoundedValues);
  }

  @Test
  public void shouldRoundPercentagesToAddUpToOneHundred() {
    List<Long> counts = ImmutableList.of(3L, 2L, 4L); //(0.3333, 0.2222, 0.4444)
    List<Integer> expectedRoundedValues = ImmutableList.of(33, 22, 45);
    assertCalculatedPercentage(counts, expectedRoundedValues);
  }

  @Test
  public void shouldRoundPercentageWithTheLeastRelativeError() {
    List<Long> counts = ImmutableList.of(303L, 202L, 495L); //(0.3030, 0.2020, 0.4950)
    List<Integer> expectedRoundedValues = ImmutableList.of(30, 20, 50);
    assertCalculatedPercentage(counts, expectedRoundedValues);
  }

  @Test
  public void shouldRoundPercentagesToMinimalizeRelativeError() {
    List<Long> counts = ImmutableList.of(92L, 704L, 204L); //(0.0920, 0.7040, 0.2040)
    List<Integer> expectedRoundedValues = ImmutableList.of(9, 71, 20);
    assertCalculatedPercentage(counts, expectedRoundedValues);
  }

  @Test
  public void shouldRoundUpTheFirstOfElementsWithTheSameRelativeError() {
    List<Long> counts = ImmutableList.of(1L, 1L, 1L); //(0.3333, 0.3333, 0.3333)
    List<Integer> expectedRoundedValues = ImmutableList.of(34, 33, 33);
    assertCalculatedPercentage(counts, expectedRoundedValues);
  }

  private void assertCalculatedPercentage(List<Long> counts, List<Integer> expectedRoundedValues) {
    List<Percentage> expectedPercentage = expectedRoundedValues.stream()
        .map(Percentage::new)
        .collect(Collectors.toList());
    List<Percentage> actualPercentage = percentageCalculator.calculatePercentage(counts);
    assertThat(actualPercentage, is(expectedPercentage));
  }

}