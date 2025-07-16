package com.ILSI.TouristeProject.Accessibilitys.model;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Fly extends Accessibility {

    private String airline;
    private String departureAirport;
    private String arrivalAirport;
}
