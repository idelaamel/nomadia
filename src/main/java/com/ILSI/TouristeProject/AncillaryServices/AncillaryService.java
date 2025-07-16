package com.ILSI.TouristeProject.AncillaryServices;

import com.ILSI.TouristeProject.AncillaryServices.model.*;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAncillaryService;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = Bank.class, name = "Bank"),
        @JsonSubTypes.Type(value = CarAgency.class, name = "CarAgency"),
        @JsonSubTypes.Type(value = TravelAgency.class, name = "TravelAgency"),
        @JsonSubTypes.Type(value = Administrative.class, name = "Administrative"),
        @JsonSubTypes.Type(value = TourGuide.class, name = "TourGuide"),
        @JsonSubTypes.Type(value = Sanitary.class, name = "Sanitary")
})
@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class AncillaryService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_AncillaryService;
    @Size(min = 4, max = 50)
    private String name;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    @Size(min = 10, max = 10)
    private String phoneNumber;
    @Size(min = 5, max = 50)
    private String email;
    @Size(min = 5, max = 50)
    private String operationHours;
    private double latitude;
    private double longitude;

    @OneToMany(mappedBy = "ancillaryService", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FavoritesAncillaryService> favoriAncillaryServices = new ArrayList<>();
}
