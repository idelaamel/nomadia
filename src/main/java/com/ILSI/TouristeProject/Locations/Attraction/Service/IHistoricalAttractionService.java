package com.ILSI.TouristeProject.Locations.Attraction.Service;

import com.ILSI.TouristeProject.Locations.Attraction.Dto.HistoricalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.model.HistoricalAttraction;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IHistoricalAttractionService {

    List<HistoricalAttraction> getAllHistoricalAttraction();

    Optional<HistoricalAttraction> findHistoricalAttractionByName(String name);

    String addHistoricalAttraction(HistoricalAttractionDto historicalAttractionDto) throws IOException;

    String UpdateHistoricalAttraction(Long historicalAttraction_id , HistoricalAttractionDto historicalAttractionDto) throws IOException;

    String DeleteHistoricalAttraction(Long historicalAttraction_id);
}
