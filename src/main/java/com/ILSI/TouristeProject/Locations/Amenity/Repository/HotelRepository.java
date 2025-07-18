package com.ILSI.TouristeProject.Locations.Amenity.Repository;

import com.ILSI.TouristeProject.Locations.Amenity.Model.Hotel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HotelRepository extends JpaRepository<Hotel,Long> {

    Optional<Hotel> findByName(String name);
}
