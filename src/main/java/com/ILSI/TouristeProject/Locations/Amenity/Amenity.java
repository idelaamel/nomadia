package com.ILSI.TouristeProject.Locations.Amenity;


import com.ILSI.TouristeProject.Locations.model.Location;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Amenity extends Location {

   private double price;
   private boolean isAvailable;
   private String openingHours;



}
