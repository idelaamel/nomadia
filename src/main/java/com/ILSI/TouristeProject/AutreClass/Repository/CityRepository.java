package com.ILSI.TouristeProject.AutreClass.Repository;

import com.ILSI.TouristeProject.AutreClass.model.City;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CityRepository extends JpaRepository<City, Long> {

    Optional<City> findCityByName(String name);
}
