package com.ILSI.TouristeProject.RecommanderSystem.Repository;

import com.ILSI.TouristeProject.Locations.model.Location;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesLocation;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritesLocationRepository extends JpaRepository<FavoritesLocation, Long> {
    boolean existsByUserAndLocation(AppUser user, Location location);

    @Query("SELECT f.location.id_Location FROM FavoritesLocation f WHERE f.user.id = :userId")
    List<Long> findLocationIdsByUserId(@Param("userId") Long userId);
}
