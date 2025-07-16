package com.ILSI.TouristeProject.RecommanderSystem.Repository;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAncillaryService;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoritesAncillaryServiceRepository extends JpaRepository<FavoritesAncillaryService, Long> {


    boolean existsByUserAndAncillaryService(AppUser user, AncillaryService ancillaryService);

    @Query("SELECT f.ancillaryService.id_AncillaryService FROM FavoritesAncillaryService f WHERE f.user.id = :userId")
    List<Long> findAncillaryServiceIdsByUserId(@Param("userId") Long userId);
}
