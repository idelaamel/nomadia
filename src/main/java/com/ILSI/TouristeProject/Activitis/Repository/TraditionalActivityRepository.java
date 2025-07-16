package com.ILSI.TouristeProject.Activitis.Repository;

import com.ILSI.TouristeProject.Activitis.model.TraditionalActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TraditionalActivityRepository extends JpaRepository<TraditionalActivity, Long> {

    Optional<TraditionalActivity> findTraditionalActivityByName(String name);
}
