package com.ILSI.TouristeProject.RecommanderSystem.Repository;

import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesActivity;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritesActivityRepository extends JpaRepository<FavoritesActivity, Long> {

    boolean existsByUserAndActivity(AppUser user, Activity activity);

    @Query("SELECT f.activity.id_Activity FROM FavoritesActivity f WHERE f.user.id = :userId")
    List<Long> findActivityIdsByUserId(@Param("userId") Long userId);
}
