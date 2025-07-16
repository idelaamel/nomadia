package com.ILSI.TouristeProject.Locations.Attraction.Repository;

import com.ILSI.TouristeProject.Locations.Attraction.model.HistoricalAttraction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HistoricalAttractionRepository extends JpaRepository<HistoricalAttraction, Long> {

    Optional<HistoricalAttraction> findHistoricalAttractionByName(String name);
}
