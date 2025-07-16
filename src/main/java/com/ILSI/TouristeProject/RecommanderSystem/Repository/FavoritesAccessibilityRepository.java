package com.ILSI.TouristeProject.RecommanderSystem.Repository;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAccessibility;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritesAccessibilityRepository extends JpaRepository<FavoritesAccessibility, Long> {


    boolean existsByUserAndAccessibility(AppUser user, Accessibility accessibility);


    @Query("SELECT f.accessibility.id_Accessibility FROM FavoritesAccessibility f WHERE f.user.id = :userId")
    List<Long> findAccessibilityIdsByUserId(@Param("userId") Long userId);
}
