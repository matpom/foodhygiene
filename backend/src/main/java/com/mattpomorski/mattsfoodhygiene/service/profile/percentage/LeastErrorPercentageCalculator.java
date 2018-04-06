package com.mattpomorski.mattsfoodhygiene.service.profile.percentage;

import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeastErrorPercentageCalculator implements PercentageCalculator {

  @Override
  public List<Percentage> calculatePercentage(List<Long> counts) {
    double[] rawFractions = getRawFractions(counts);
    int[] rounded = roundToPercentages(rawFractions);

    return Arrays.stream(rounded)
        .boxed()
        .map(Percentage::new)
        .collect(Collectors.toList());
  }

  private double[] getRawFractions(List<Long> counts) {
    double total = counts.stream().mapToLong(n -> n).sum();
    return counts.stream().mapToDouble(count -> count / total).toArray();
  }

  private int[] roundToPercentages(double[] fractions) {
    double[] precisePercentages = transformToPrecisePercentages(fractions);
    int[] integerPercentages = castToIntegerValues(precisePercentages);
    return round(integerPercentages, precisePercentages);
  }

  private int[] round(int[] integerPercentages, double[] precisePercentages) {
    if (addUpToOneHundred(integerPercentages)) {
      return integerPercentages;
    }
    int[] roundedPercentages = integerPercentages.clone();
    while (!addUpToOneHundred(roundedPercentages)) {
      incrementPercentageWithLeastError(roundedPercentages, precisePercentages);
    }
    return roundedPercentages;
  }

  private void incrementPercentageWithLeastError(int[] integerPercentages, double[] precisePercentages) {
    int leastErrorIndex = 0;
    double leastError = Double.MAX_VALUE;
    for (int i = 0; i < integerPercentages.length; i++) {
      double currentError = (integerPercentages[i] + 1 - precisePercentages[i]) / precisePercentages[i];
      if (currentError < leastError) {
        leastError = currentError;
        leastErrorIndex = i;
      }
    }
    integerPercentages[leastErrorIndex] = integerPercentages[leastErrorIndex] + 1;
  }

  private boolean addUpToOneHundred(int[] percentages) {
    int total = 0;
    for (int value : percentages) {
      total += value;
    }
    return total == 100;
  }

  private int[] castToIntegerValues(double[] precisePercentages) {
    int[] roundedPercentages = new int[precisePercentages.length];
    for (int i = 0; i < roundedPercentages.length; i++) {
      roundedPercentages[i] = (int) precisePercentages[i];
    }
    return roundedPercentages;
  }

  private double[] transformToPrecisePercentages(double[] fractions) {
    double[] precisePercentages = new double[fractions.length];
    for (int i = 0; i < precisePercentages.length; i++) {
      precisePercentages[i] = fractions[i] * 100;
    }
    return precisePercentages;
  }
}
