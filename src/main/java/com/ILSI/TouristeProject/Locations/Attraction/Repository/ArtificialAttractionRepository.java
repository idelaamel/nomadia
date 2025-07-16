package com.ILSI.TouristeProject.Locations.Attraction.Repository;

import com.ILSI.TouristeProject.Locations.Attraction.model.ArtificialAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtificialAttractionRepository extends JpaRepository<ArtificialAttraction,Long> {

    Optional<ArtificialAttraction> findArtificialAttractionByName(String name);
}
