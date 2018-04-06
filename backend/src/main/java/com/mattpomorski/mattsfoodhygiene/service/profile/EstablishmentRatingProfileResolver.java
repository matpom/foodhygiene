package com.mattpomorski.mattsfoodhygiene.service.profile;

import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Rating;
import com.mattpomorski.mattsfoodhygiene.service.profile.percentage.PercentageCalculator;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class EstablishmentRatingProfileResolver implements ProfileResolver<Establishment, Rating> {

  private final PercentageCalculator percentageCalculator;

  public EstablishmentRatingProfileResolver(PercentageCalculator percentageCalculator) {
    this.percentageCalculator = percentageCalculator;
  }

  @Override
  public Map<Rating, Percentage> resolveRatingProfile(Collection<Establishment> collection) {
    if (collection.isEmpty()) {
      return Collections.emptyMap();
    }
    Map<Rating, Long> ratingCounts = getCounts(collection);

    List<Rating> possibleRatings = new ArrayList<>();
    List<Long> values = new ArrayList<>();

    ratingCounts.forEach((key, value) -> {
      possibleRatings.add(key);
      values.add(value);
    });

    return zip(possibleRatings, percentageCalculator.calculatePercentage(values));
  }

  private Map<Rating, Long> getCounts(Collection<Establishment> collection) {
    return collection.stream()
        .collect(Collectors.groupingBy(Establishment::getRating, Collectors.counting()));
  }

  private Map<Rating, Percentage> zip(List<Rating> possibleRatings,
                                      List<Percentage> percentage) {
    return IntStream.range(0, possibleRatings.size()).boxed()
        .collect(Collectors.toMap(possibleRatings::get, percentage::get));
  }

}
