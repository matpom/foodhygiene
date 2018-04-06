package com.mattpomorski.mattsfoodhygiene.service;

import com.mattpomorski.mattsfoodhygiene.client.FoodHygieneSupplier;
import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.EstablishmentsProfileException;
import com.mattpomorski.mattsfoodhygiene.domain.exceptions.LocalAuthoritiesException;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfile;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfileEntry;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Rating;
import com.mattpomorski.mattsfoodhygiene.service.profile.ProfileResolver;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class FoodHygieneServiceImpl implements FoodHygieneService {

  private final FoodHygieneSupplier foodHygieneSupplier;
  private final ProfileResolver<Establishment, Rating> profileResolver;

  public FoodHygieneServiceImpl(FoodHygieneSupplier foodHygieneSupplier,
                                ProfileResolver<Establishment, Rating> profileResolver) {
    this.foodHygieneSupplier = foodHygieneSupplier;
    this.profileResolver = profileResolver;
  }

  @Override
  public List<LocalAuthority> getLocalAuthorities() {
    try {
      return foodHygieneSupplier.getLocalAuthorities();
    } catch (Exception ex) {
      throw new LocalAuthoritiesException("Unable to get local authorities", ex);
    }
  }

  @Override
  public EstablishmentsProfile getEstablishmentsProfile(long localAuthorityId) {
    try {
      List<Establishment> establishments = foodHygieneSupplier.getEstablishments(localAuthorityId);
      Map<Rating, Percentage> ratingProfile = profileResolver.resolveRatingProfile(establishments);

      List<EstablishmentsProfileEntry> profileEntries =
          ratingProfile.entrySet().stream()
          .map(entry -> new EstablishmentsProfileEntry(entry.getKey(), entry.getValue()))
          .sorted(Comparator.comparing(EstablishmentsProfileEntry::getRating))
          .collect(Collectors.toList());

      return new EstablishmentsProfile(localAuthorityId, profileEntries);
    } catch (Exception ex) {
      throw new EstablishmentsProfileException("Unable to get establishment profile", ex);
    }
  }
}
