package com.ILSI.TouristeProject.AvailablePackages.model;

import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.AutreClass.model.Review;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAvailablePackage;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
public class AvailablePackage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_AvailablePackage;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private int duration;
    private double price;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private int capacity;


    @OneToMany(mappedBy = "availablePackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "avPackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @ManyToMany(mappedBy = "viewedPackages")
    private Set<AppUser> usersViewed = new HashSet<>();

    @OneToMany(mappedBy = "availablePackage", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesAvailablePackage> favoriAvailaiblePackages = new ArrayList<>();


    public void addImage(Image image) {
        image.setAvPackage(this);
        this.images.add(image);
    }

}
