package com.ILSI.TouristeProject.Accessibilitys;

import com.ILSI.TouristeProject.Accessibilitys.model.*;
import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.AutreClass.model.Itinerary;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAccessibility;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bus.class, name = "Bus"),
        @JsonSubTypes.Type(value = Fly.class, name = "Fly"),
        @JsonSubTypes.Type(value = Taxi.class, name = "Taxi")
})
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Accessibility {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id_Accessibility;
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private double price;
    private LocalDateTime departureTime;
    private LocalDateTime arrivalTime;


    @OneToMany(mappedBy = "accessibility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Image> images = new ArrayList<>();

    // Departure City
    @ManyToOne
    @JoinColumn(name = "departure_city_id")
    private City departureCity;

    // Arrival City
    @ManyToOne
    @JoinColumn(name = "arrival_city_id")
    private City arrivalCity;

    @ManyToMany(mappedBy = "accessibilityList")
    private List<Itinerary> itineraries = new ArrayList<>();

    // Liste des utilisateurs qui ont mis ce produit en favori
    @OneToMany(mappedBy = "accessibility", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesAccessibility> favoriAccessibilities = new ArrayList<>();


    public void addImage(Image image) {
        image.setAccessibility(this);
        this.images.add(image);
    }
}



