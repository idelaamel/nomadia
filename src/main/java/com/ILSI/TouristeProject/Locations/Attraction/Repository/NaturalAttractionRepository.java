package com.ILSI.TouristeProject.Locations.Attraction.Repository;

import com.ILSI.TouristeProject.Locations.Attraction.model.NaturalAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface NaturalAttractionRepository extends JpaRepository<NaturalAttraction, Long> {

    Optional<NaturalAttraction> findNaturalAttractionByName(String name);

}
