package com.ILSI.TouristeProject.Locations.Amenity.Model;

import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Camping extends Amenity {

    private int capacity; //ors
    private boolean ElectricityAvailability;
    private boolean SanitaryAvailability;
    private boolean hasWaterSupply;
}
