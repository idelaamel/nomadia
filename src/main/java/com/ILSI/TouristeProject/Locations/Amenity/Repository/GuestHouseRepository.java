package com.ILSI.TouristeProject.Locations.Amenity.Repository;

import com.ILSI.TouristeProject.Locations.Amenity.Model.GuestHouse;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GuestHouseRepository extends JpaRepository<GuestHouse,Long> {

    Optional<GuestHouse> findByName(String name);
}
