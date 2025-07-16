package com.ILSI.TouristeProject.Locations.Amenity.Repository;

import com.ILSI.TouristeProject.Locations.Amenity.Model.Camping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CampingRepository extends JpaRepository<Camping, Long> {

    Optional<Camping> findByName(String name);
}
