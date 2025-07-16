package com.ILSI.TouristeProject.Activitis.model;

import com.ILSI.TouristeProject.Activitis.Activity;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
@Entity
public class AdventureActivity extends Activity {

    private String terrainType;
    private int ageRestriction;

}
