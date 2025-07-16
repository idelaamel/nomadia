package com.ILSI.TouristeProject.AncillaryServices.model;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import jakarta.persistence.Entity;
import lombok.Data;

@Entity
@Data
public class Sanitary extends AncillaryService {

    private boolean emergencySupport;
}
