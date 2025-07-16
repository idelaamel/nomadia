package com.ILSI.TouristeProject.AutreClass.Repository;

import com.ILSI.TouristeProject.AutreClass.model.Itinerary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ItineraryRepository extends JpaRepository<Itinerary, Long> {

    Optional<Itinerary> findItineraryByName(String name);
}
