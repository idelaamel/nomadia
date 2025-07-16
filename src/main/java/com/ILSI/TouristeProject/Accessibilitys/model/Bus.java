package com.ILSI.TouristeProject.Accessibilitys.model;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class Bus extends Accessibility {
    private String company;
    private String routeName;
}
