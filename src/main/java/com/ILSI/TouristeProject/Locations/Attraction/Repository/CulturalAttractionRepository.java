package com.ILSI.TouristeProject.Locations.Attraction.Repository;

import com.ILSI.TouristeProject.Locations.Attraction.model.CulturalAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CulturalAttractionRepository extends JpaRepository<CulturalAttraction, Long> {

    Optional<CulturalAttraction> findCulturalAttractionByName(String name);
}
