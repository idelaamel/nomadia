package com.ILSI.TouristeProject.AutreClass.model;


import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Locations.model.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class City {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_City;

    @Column(name = "City_name", nullable = false, length = 50)
    private String name;

    // a City Belongs to a Country
    @ManyToOne
    @JoinColumn(name = "id_Country")
    private Country country;

    // a City have a lot of Locations
    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL)
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "departureCity", cascade = CascadeType.ALL)
    private List<Accessibility> departureAccessibilityList = new ArrayList<>();

    @OneToMany(mappedBy = "arrivalCity", cascade = CascadeType.ALL)
    private List<Accessibility> ArrivalAccessibilityList = new ArrayList<>();


    public void addLocation(Location location) {
        location.setCity(this);
        this.locations.add(location);
    }

    public void addAccessibilityToDepartureCity(Accessibility accessibility) {
        accessibility.setDepartureCity(this);
        this.departureAccessibilityList.add(accessibility);
    }

    public void addAccessibilityToArrivalCity(Accessibility accessibility) {
        accessibility.setArrivalCity(this);
        this.ArrivalAccessibilityList.add(accessibility);
    }

}
