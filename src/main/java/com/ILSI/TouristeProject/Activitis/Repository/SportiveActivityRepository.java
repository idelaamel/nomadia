package com.ILSI.TouristeProject.Activitis.Repository;

import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SportiveActivityRepository extends JpaRepository<SportiveActivity , Long> {

    Optional<SportiveActivity> findSportiveActivityByName(String name);

}
