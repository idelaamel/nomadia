package com.ILSI.TouristeProject.Accessibilitys.Repository;

import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FlyRepository extends JpaRepository<Fly, Long> {

    Optional<Fly> findByName(String name);

}
