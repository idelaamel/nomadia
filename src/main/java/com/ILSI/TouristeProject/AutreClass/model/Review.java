package com.ILSI.TouristeProject.AutreClass.model;

import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import jakarta.persistence.*;
import lombok.Data;


import java.time.LocalDateTime;

@Data
@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_review;

    @Lob
    @Column(columnDefinition = "TEXT")
    private String content;
    private LocalDateTime date_review;
    private int rating;

    @ManyToOne
    @JoinColumn(name = "AvailablePackage_id")
    private AvailablePackage availablePackage;

}
