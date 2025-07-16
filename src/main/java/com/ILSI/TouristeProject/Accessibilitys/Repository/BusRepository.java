package com.ILSI.TouristeProject.Accessibilitys.Repository;

import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BusRepository extends JpaRepository<Bus,Long> {

    Optional<Bus> findByName(String name);
}
