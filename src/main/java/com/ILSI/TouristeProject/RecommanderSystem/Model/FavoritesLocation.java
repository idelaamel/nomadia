package com.ILSI.TouristeProject.RecommanderSystem.Model;

import com.ILSI.TouristeProject.Locations.model.Location;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Favorites_Location")
@Data
public class FavoritesLocation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;
}
