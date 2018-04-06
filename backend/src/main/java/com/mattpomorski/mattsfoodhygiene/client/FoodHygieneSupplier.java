package com.mattpomorski.mattsfoodhygiene.client;

import com.mattpomorski.mattsfoodhygiene.domain.Establishment;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;

import java.util.List;

public interface FoodHygieneSupplier {

  List<LocalAuthority> getLocalAuthorities();

  List<Establishment> getEstablishments(long localAuthorityId);

}
