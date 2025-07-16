package com.ILSI.TouristeProject.Locations.Attraction.model;

import com.ILSI.TouristeProject.Locations.Attraction.Attraction;
import jakarta.persistence.Entity;
import lombok.Data;


@Data
@Entity
public class ArtificialAttraction extends Attraction {

    private String yearBuild;
}
