package com.ILSI.TouristeProject.Locations.model;


import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.Amenity.Model.*;
import com.ILSI.TouristeProject.Locations.Attraction.model.ArtificialAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.CulturalAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.HistoricalAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.NaturalAttraction;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesLocation;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Cafe.class, name = "Cafe"),
        @JsonSubTypes.Type(value = Camping.class, name = "Camping"),
        @JsonSubTypes.Type(value = GuestHouse.class, name = "Guest House "),
        @JsonSubTypes.Type(value = Hotel.class, name = "Hotel"),
        @JsonSubTypes.Type(value = Lodge.class, name = "Lodge"),
        @JsonSubTypes.Type(value = Restaurant.class, name = "Restaurant"),
        @JsonSubTypes.Type(value = ArtificialAttraction.class, name = "ArtificialAttraction"),
        @JsonSubTypes.Type(value = CulturalAttraction.class, name = "CulturalAttraction"),
        @JsonSubTypes.Type(value = HistoricalAttraction.class, name = "HistoricalAttraction"),
        @JsonSubTypes.Type(value = NaturalAttraction.class, name = "NaturalAttraction"),
})
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Location;

    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;

    private double Longitude;

    private double Latitude;

    @ManyToOne
    @JoinColumn(name = "id_City") // foreign key in the Location table
    private City city;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesLocation> favoriLocations = new ArrayList<>();


    public void addImage(Image image) {
        image.setLocation(this);
        this.images.add(image);
    }

}
