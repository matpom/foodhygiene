package com.mattpomorski.mattsfoodhygiene.domain.profile;

import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

import org.junit.Test;

public class RatingTest {

  @Test
  public void integerRatingShouldHaveHigherPrecedence() {
    assertThat(new Rating("1").compareTo(new Rating("Exempt")), lessThan(0));
    assertThat(new Rating("Exempt").compareTo(new Rating("5")), greaterThan(0));
  }

  @Test
  public void shouldReturnZeroForEqualRatings() {
    assertThat(new Rating("5").compareTo(new Rating("5")), is(0));
  }

  @Test
  public void integerRatingsShouldHaveNaturalPrecedence() {
    assertThat(new Rating("5").compareTo(new Rating("4")), lessThan(0));
    assertThat(new Rating("1").compareTo(new Rating("3")), greaterThan(0));
  }

  @Test
  public void stringRatingShouldHaveNaturalStringOrdering() {
    assertThat(new Rating("aa").compareTo(new Rating("bb")), lessThan(0));
  }

}