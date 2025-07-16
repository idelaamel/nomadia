package com.ILSI.TouristeProject.AutreClass.Repository;

import com.ILSI.TouristeProject.AutreClass.model.Step;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StepRepository extends JpaRepository<Step, Long> {

    Optional<Step> findStepByTitle(String title);
}
