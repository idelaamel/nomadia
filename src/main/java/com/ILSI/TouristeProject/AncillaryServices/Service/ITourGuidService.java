package com.ILSI.TouristeProject.AncillaryServices.Service;

import com.ILSI.TouristeProject.AncillaryServices.dto.TourGuideDto;
import com.ILSI.TouristeProject.AncillaryServices.model.TourGuide;

import java.util.List;
import java.util.Optional;

public interface ITourGuidService {

    List<TourGuide> findAllTourGuides();

    Optional<TourGuide> findTourGuidByName(String name);

    String addTourGuide(TourGuideDto tourGuideDto);

    String updateTourGuide(Long tourGuide_id , TourGuideDto tourGuideDto);

    String deleteTourGuide(Long tourGuide_id);
}
