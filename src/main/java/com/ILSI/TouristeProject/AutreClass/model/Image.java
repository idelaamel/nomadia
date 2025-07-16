package com.ILSI.TouristeProject.AutreClass.model;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.Locations.model.Location;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Entity
@Data
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

//    @Temporal(TemporalType.TIMESTAMP)
//    private Date uploadedAt;

    @ManyToOne
    @JoinColumn(name = "location_id")
    @JsonIgnore
    private Location location;

    @ManyToOne
    @JoinColumn(name = "activity_id")
    @JsonIgnore
    private Activity activity;

    @ManyToOne
    @JoinColumn(name = "Accessinility_id")
    @JsonIgnore
    private Accessibility accessibility;

    @ManyToOne
    @JoinColumn(name = "AvailablePackge_id")
    @JsonIgnore
    private AvailablePackage avPackage;
}
