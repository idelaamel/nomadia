package com.ILSI.TouristeProject.Accessibilitys.model;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Taxi extends Accessibility {

    private int taxiNumber;
    private int capacity;

}
