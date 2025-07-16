package com.ILSI.TouristeProject.AutreClass.Repository;

import com.ILSI.TouristeProject.AutreClass.dto.VisitDto;
import com.ILSI.TouristeProject.AutreClass.model.Visit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface VisitRepository extends JpaRepository<Visit, Long> {

        Optional<Visit> findVisitByTitle(String title);
}
