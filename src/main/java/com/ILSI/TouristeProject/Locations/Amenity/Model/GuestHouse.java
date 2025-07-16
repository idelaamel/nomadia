package com.ILSI.TouristeProject.Locations.Amenity.Model;

import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class GuestHouse extends Amenity {

    private int numberRooms;
    private boolean BreakfastIncluded;
}
