package com.ILSI.TouristeProject.AncillaryServices.model;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Administrative extends AncillaryService {

    private boolean requiresReservation;
    private String documentRequired;
}
