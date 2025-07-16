package com.ILSI.TouristeProject.RecommanderSystem.Model;

import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Favorites_AvailablePackage")
@Data
public class FavoritesAvailablePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "package_id")
    private AvailablePackage availablePackage;
}
