package com.ILSI.TouristeProject.RecommanderSystem.Model;

import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "Favorites_Activity")
@Data
public class FavoritesActivity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    private Activity activity;
}
