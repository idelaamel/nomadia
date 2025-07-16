package com.ILSI.TouristeProject.Activitis.Repository;

import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdventureActivityRepository extends JpaRepository<AdventureActivity, Long> {

   Optional <AdventureActivity> findAdventureActivityByName(String name);
}
