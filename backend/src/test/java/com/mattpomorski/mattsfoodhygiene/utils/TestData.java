package com.mattpomorski.mattsfoodhygiene.utils;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.mattpomorski.mattsfoodhygiene.client.dto.authority.AuthoritiesResponseV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.authority.AuthorityV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.establishment.EstablishmentV2;
import com.mattpomorski.mattsfoodhygiene.client.dto.establishment.EstablishmentsResponseV2;
import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfile;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfileEntry;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Percentage;
import com.mattpomorski.mattsfoodhygiene.domain.profile.Rating;

import java.util.List;
import java.util.Map;

public class TestData {

  public static final List<LocalAuthority> SAMPLE_LOCAL_AUTHORITIES = ImmutableList.of(
      new LocalAuthority(197, "Aberdeen City"),
      new LocalAuthority(96, "Ealing"),
      new LocalAuthority(26, "Watford")
  );

  public static final AuthoritiesResponseV2 SAMPLE_AUTHORITIES_RESPONSE =
      new AuthoritiesResponseV2(ImmutableList.of(
          new AuthorityV2(197, "Aberdeen City"),
          new AuthorityV2(96, "Ealing"),
          new AuthorityV2(26, "Watford")
      ));

  public static final long SAMPLE_LOCAL_AUTHORITY_ID = 97;

  public static final List<Establishment> SAMPLE_ESTABLISHMENTS = ImmutableList.of(
      new Establishment(766323, "4"),
      new Establishment(91478, "5"),
      new Establishment(800626, "Exempt"),
      new Establishment(513961, "2"),
      new Establishment(75867, "4"),
      new Establishment(838655, "4"),
      new Establishment(816480, "3"),
      new Establishment(67911, "1"),
      new Establishment(644310, "4")
  );

  public static final List<Establishment> SIMPLE_ESTABLISHMENTS = ImmutableList.of(
      new Establishment(766323, "4"),
      new Establishment(91478, "5"),
      new Establishment(800626, "5"),
      new Establishment(513961, "Exempt")
  );

  public static final Map<Rating, Percentage> SIMPLE_PROFILE = ImmutableMap.of(
      new Rating("4"), new Percentage(25),
      new Rating("5"), new Percentage(50),
      new Rating("Exempt"), new Percentage(25)
  );

  public static final EstablishmentsProfile SIMPLE_ESTABLISHMENTS_PROFILE =
      new EstablishmentsProfile(
          SAMPLE_LOCAL_AUTHORITY_ID,
          ImmutableList.of(
              new EstablishmentsProfileEntry(new Rating("5"), new Percentage(50)),
              new EstablishmentsProfileEntry(new Rating("4"), new Percentage(25)),
              new EstablishmentsProfileEntry(new Rating("Exempt"), new Percentage(25))
          )
      );

  public static final EstablishmentsResponseV2 SAMPLE_ESTABLISHMENTS_RESPONSE =
      new EstablishmentsResponseV2(ImmutableList.of(
          new EstablishmentV2(766323, "4"),
          new EstablishmentV2(91478, "5"),
          new EstablishmentV2(800626, "Exempt"),
          new EstablishmentV2(513961, "2"),
          new EstablishmentV2(75867, "4"),
          new EstablishmentV2(838655, "4"),
          new EstablishmentV2(816480, "3"),
          new EstablishmentV2(67911, "1"),
          new EstablishmentV2(644310, "4")
      ));
}
