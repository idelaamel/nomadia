package com.ILSI.TouristeProject.Locations.Amenity.Model;

import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Hotel extends Amenity {

    private int numberStars;
    private int numberOfRooms;
    private boolean hasSwimmingPool;
}
