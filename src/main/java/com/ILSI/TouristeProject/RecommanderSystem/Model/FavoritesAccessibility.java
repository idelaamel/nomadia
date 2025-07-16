package com.ILSI.TouristeProject.RecommanderSystem.Model;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Favorites_Accessibility")
@Data
public class FavoritesAccessibility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "accessibility_id")
    private Accessibility accessibility;
}
