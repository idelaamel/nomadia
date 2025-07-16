package com.ILSI.TouristeProject.AncillaryServices.Repository;

import com.ILSI.TouristeProject.AncillaryServices.model.CarAgency;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CarAgencyRepository extends JpaRepository<CarAgency , Long > {

    Optional<CarAgency> findCarAgencyByName(String name);
}
