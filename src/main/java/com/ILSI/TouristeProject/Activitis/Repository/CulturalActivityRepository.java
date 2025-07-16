package com.ILSI.TouristeProject.Activitis.Repository;

import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CulturalActivityRepository extends JpaRepository<CulturalActivity, Long> {

    Optional<CulturalActivity> findCulturalActivityByName(String name);
}
