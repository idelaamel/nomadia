package com.ILSI.TouristeProject.Locations.Attraction.Service;

import com.ILSI.TouristeProject.Locations.Attraction.Dto.NaturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.model.NaturalAttraction;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface INaturalAttractionService {

    List<NaturalAttraction> getAllNaturalAttraction();

    Optional<NaturalAttraction> findNaturalAttractionByName(String name);

    String addNaturalAttraction(NaturalAttractionDto naturalAttractionDto) throws IOException;

    String UpdateNaturalAttraction(Long naturalAttraction_id , NaturalAttractionDto naturalAttractionDto) throws IOException;

    String DeleteNaturalAttraction(Long naturalAttraction_id);
}
