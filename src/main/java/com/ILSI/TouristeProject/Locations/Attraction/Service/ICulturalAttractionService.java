package com.ILSI.TouristeProject.Locations.Attraction.Service;

import com.ILSI.TouristeProject.Locations.Attraction.Dto.CulturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.model.CulturalAttraction;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICulturalAttractionService {

    List<CulturalAttraction> getAllCulturalAttraction();

    Optional<CulturalAttraction> findCulturalAttractionByName(String name);

    String addCulturalAttraction(CulturalAttractionDto culturalAttractionDto) throws IOException;

    String UpdateCulturalAttraction(Long culturalAttraction_id ,CulturalAttractionDto culturalAttractionDto) throws IOException;

    String DeleteCulturalAttraction(Long culturalAttraction_id);
}
