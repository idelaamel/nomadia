package com.ILSI.TouristeProject.Locations.Attraction.Service;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.ArtificialAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.model.ArtificialAttraction;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IArtificialAttractionService {

    List<ArtificialAttraction> getAllArtificialAttractions();

    Optional<ArtificialAttraction> findArtificialAttractionByName(String name);

    String addArtificialAttraction(ArtificialAttractionDto artificialAttractionDto) throws IOException;

    String UpdateArtificialAttraction(Long artificialAttraction_id , ArtificialAttractionDto artificialAttractionDto) throws IOException;

    String DeleteArtificialAttraction(Long artificialAttraction_id);

}
