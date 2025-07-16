package com.ILSI.TouristeProject.AncillaryServices.model;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class CarAgency extends AncillaryService {

    private String[] vehiclesType;
    private boolean insuranceIncluded;
    private String webSiteUrl;

}
