package com.ILSI.TouristeProject.Locations.Amenity.Repository;

import com.ILSI.TouristeProject.Locations.Amenity.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RestaurantRepository extends JpaRepository<Restaurant, Long> {

    Optional<Restaurant> findByName(String name);

}
