package com.ILSI.TouristeProject.AncillaryServices.Repository;


import com.ILSI.TouristeProject.AncillaryServices.model.TravelAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TravelAgencyRepository extends JpaRepository<TravelAgency, Long> {
    Optional<TravelAgency> findTravelAgencyByName(String name);
}
