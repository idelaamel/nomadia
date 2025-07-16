package com.ILSI.TouristeProject.AutreClass.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Country;

    @Column(name = "name_country", nullable = false, length = 50)
    private String name;

    // A country can have multiple cities.
    @OneToMany(mappedBy = "country")
    private List<City> cities= new ArrayList<>();

    public void addCity(City city) {
        city.setCountry(this);
        this.cities.add(city);
    }
}
