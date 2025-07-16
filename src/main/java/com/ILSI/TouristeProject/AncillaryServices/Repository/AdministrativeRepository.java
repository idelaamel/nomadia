package com.ILSI.TouristeProject.AncillaryServices.Repository;

import com.ILSI.TouristeProject.AncillaryServices.model.Administrative;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdministrativeRepository extends JpaRepository<Administrative, Long> {

    Optional<Administrative> findAdministrativeByName(String name);
}
