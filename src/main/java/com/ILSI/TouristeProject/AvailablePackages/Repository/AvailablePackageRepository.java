package com.ILSI.TouristeProject.AvailablePackages.Repository;

import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvailablePackageRepository extends JpaRepository<AvailablePackage, Long> {

    Optional<AvailablePackage> findByName(String name);
}
