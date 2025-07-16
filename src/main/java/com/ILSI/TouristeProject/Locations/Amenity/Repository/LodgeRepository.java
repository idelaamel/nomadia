package com.ILSI.TouristeProject.Locations.Amenity.Repository;

import com.ILSI.TouristeProject.Locations.Amenity.Model.Lodge;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LodgeRepository extends JpaRepository<Lodge, Long> {

    Optional<Lodge> findByName(String name);
}
