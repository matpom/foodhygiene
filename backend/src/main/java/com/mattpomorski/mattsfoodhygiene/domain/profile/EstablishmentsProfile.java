package com.mattpomorski.mattsfoodhygiene.domain.profile;

import java.util.List;
import java.util.Objects;

public class EstablishmentsProfile {

  private final long localAuthorityId;
  private final List<EstablishmentsProfileEntry> ratingDistribution;

  public EstablishmentsProfile(long localAuthorityId, List<EstablishmentsProfileEntry> ratingDistribution) {
    this.localAuthorityId = localAuthorityId;
    this.ratingDistribution = ratingDistribution;
  }

  public long getLocalAuthorityId() {
    return localAuthorityId;
  }

  public List<EstablishmentsProfileEntry> getRatingDistribution() {
    return ratingDistribution;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    EstablishmentsProfile that = (EstablishmentsProfile) o;
    return localAuthorityId == that.localAuthorityId &&
        Objects.equals(ratingDistribution, that.ratingDistribution);
  }

  @Override
  public int hashCode() {

    return Objects.hash(localAuthorityId, ratingDistribution);
  }

  @Override
  public String toString() {
    return "EstablishmentsProfile{" +
        "localAuthorityId=" + localAuthorityId +
        ", ratingDistribution=" + ratingDistribution +
        '}';
  }
}
