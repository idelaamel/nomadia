package com.ILSI.TouristeProject.Activitis;


import com.ILSI.TouristeProject.Activitis.model.*;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesActivity;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = AdventureActivity.class, name = "Adventure Activity"),
        @JsonSubTypes.Type(value = CulturalActivity.class, name = "Cultural Activity"),
        @JsonSubTypes.Type(value = SportiveActivity.class, name = "Sportive Activity"),
        @JsonSubTypes.Type(value = TraditionalActivity.class, name = "Traditional Activity"),
})
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Activity {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id_Activity;

    private String name;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private int ageLimit;

    private String duration;

    private double price;

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "activity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesActivity> favoriActivity = new ArrayList<>();

//    @ManyToMany(mappedBy = "activities")
//    private ArrayList<Step> Steps = new ArrayList<>();

    public void addImage(Image image) {
        image.setActivity(this);
        this.images.add(image);
    }

}
