package com.ILSI.TouristeProject.AncillaryServices.Repository;

import com.ILSI.TouristeProject.AncillaryServices.model.Sanitary;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SanitaryRepository extends JpaRepository<Sanitary, Long> {

    Optional<Sanitary> findSanitaryByName(String name);
}
