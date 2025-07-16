package com.ILSI.TouristeProject.Locations.Attraction;

import com.ILSI.TouristeProject.Locations.model.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Attraction extends Location {

    private double entryFre;
    private boolean guideToursAvailable;
}
