package com.ILSI.TouristeProject.AncillaryServices.Repository;

import com.ILSI.TouristeProject.AncillaryServices.model.TourGuide;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TourGuideRepository extends JpaRepository<TourGuide , Long > {

    Optional<TourGuide> findTourGuideByName(String name);
}
