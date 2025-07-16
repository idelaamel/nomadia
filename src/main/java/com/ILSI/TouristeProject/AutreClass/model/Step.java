package com.ILSI.TouristeProject.AutreClass.model;

import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Locations.model.Location;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Step {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_step;
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private int numberDays;


    @ManyToOne
    @JoinColumn(name = "itinerary_id", nullable = false)
    private Itinerary itinerary;

    @ManyToMany
    @JoinTable(
            name = "step_activity",
            joinColumns = @JoinColumn(name = "step_id"),
            inverseJoinColumns = @JoinColumn(name = "activity_id")
    )
    private List<Activity> activities = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location;

}
