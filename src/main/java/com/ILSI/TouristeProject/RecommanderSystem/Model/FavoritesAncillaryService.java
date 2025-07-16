package com.ILSI.TouristeProject.RecommanderSystem.Model;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Favorites_AncillaryService")
@Data
public class FavoritesAncillaryService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "ancillaryService_id")
    private AncillaryService ancillaryService;
}
