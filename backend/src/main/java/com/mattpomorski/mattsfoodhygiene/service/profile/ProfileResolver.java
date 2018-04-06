package com.mattpomorski.mattsfoodhygiene.service.profile;

import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;

import java.util.Collection;
import java.util.Map;

public interface ProfileResolver<T, VALUE_TYPE> {

  Map<VALUE_TYPE, Percentage> resolveRatingProfile(Collection<T> collection);

}
