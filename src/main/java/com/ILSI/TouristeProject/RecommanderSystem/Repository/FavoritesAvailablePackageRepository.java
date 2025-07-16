package com.ILSI.TouristeProject.RecommanderSystem.Repository;

import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAvailablePackage;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritesAvailablePackageRepository extends JpaRepository<FavoritesAvailablePackage, Long> {


    boolean existsByUserAndAvailablePackage(AppUser user, AvailablePackage availablePackage);

    @Query("SELECT f.availablePackage.id_AvailablePackage FROM FavoritesAvailablePackage f WHERE f.user.id = :userId")
    List<Long> findAvailablePackageIdsByUserId(@Param("userId") Long userId);
}
