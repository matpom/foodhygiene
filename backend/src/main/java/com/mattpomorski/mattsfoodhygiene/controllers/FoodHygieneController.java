package com.mattpomorski.mattsfoodhygiene.controllers;

import com.mattpomorski.mattsfoodhygiene.controllers.exceptions.IllegalRequestParameter;
import com.mattpomorski.mattsfoodhygiene.domain.LocalAuthority;
import com.mattpomorski.mattsfoodhygiene.domain.profile.EstablishmentsProfile;
import com.mattpomorski.mattsfoodhygiene.service.FoodHygieneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class FoodHygieneController {

  private final FoodHygieneService foodHygieneService;

  public FoodHygieneController(FoodHygieneService foodHygieneService) {
    this.foodHygieneService = foodHygieneService;
  }

  @GetMapping("/localAuthorities")
  public List<LocalAuthority> localAuthorities() {
    return foodHygieneService.getLocalAuthorities();
  }

  @GetMapping("/establishmentsProfile")
  public EstablishmentsProfile establishmentsProfile(@RequestParam String localAuthorityId) {
    try {
      return foodHygieneService.getEstablishmentsProfile(Long.parseLong(localAuthorityId));
    } catch (NumberFormatException ex) {
      throw new IllegalRequestParameter("Local authority "
          + localAuthorityId + " is not a long number");
    }
  }
}
