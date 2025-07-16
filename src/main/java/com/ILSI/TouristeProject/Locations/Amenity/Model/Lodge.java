package com.ILSI.TouristeProject.Locations.Amenity.Model;

import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Lodge extends Amenity {

    private boolean viewPanoramic;
    private boolean closeNature;

}
