package com.ILSI.TouristeProject.AutreClass.model;

import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Visit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_Visit;
    private String title;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Integer numberDays;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private AppUser user;

    @OneToMany(mappedBy = "visit", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Itinerary> itineraries = new ArrayList<>();
}
