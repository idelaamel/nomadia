package com.ILSI.TouristeProject.AutreClass.Repository;

import com.ILSI.TouristeProject.AutreClass.model.Country;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CountryRepository extends JpaRepository<Country, Long> {

   Optional <Country> findCountryByName(String name);
}
