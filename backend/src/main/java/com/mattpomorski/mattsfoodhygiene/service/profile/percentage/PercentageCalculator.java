package com.mattpomorski.mattsfoodhygiene.service.profile.percentage;

import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;

import java.util.List;

public interface PercentageCalculator {

  List<Percentage> calculatePercentage(List<Long> counts);

}