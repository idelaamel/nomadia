package com.ILSI.TouristeProject.Accessibilitys.Repository;

import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaxiRepository extends JpaRepository<Taxi,Long> {

    Optional<Taxi> findByName(String name);
}
