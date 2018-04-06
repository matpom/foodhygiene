package com.mattpomorski.mattsfoodhygiene.service;

import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfile;

import java.util.List;

public interface FoodHygieneService {

  List<LocalAuthority> getLocalAuthorities();

  EstablishmentsProfile getEstablishmentsProfile(long localAuthorityId);

}
