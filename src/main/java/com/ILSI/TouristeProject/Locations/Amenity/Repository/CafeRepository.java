package com.ILSI.TouristeProject.Locations.Amenity.Repository;

import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CafeRepository extends JpaRepository<Cafe, Long> {

    Optional<Cafe> findByName(String name);

}
