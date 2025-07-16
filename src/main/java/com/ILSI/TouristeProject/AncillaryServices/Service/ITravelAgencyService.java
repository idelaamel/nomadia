package com.ILSI.TouristeProject.AncillaryServices.Service;

import com.ILSI.TouristeProject.AncillaryServices.dto.TravelAgencyDto;
import com.ILSI.TouristeProject.AncillaryServices.model.TravelAgency;

import java.util.List;
import java.util.Optional;

public interface ITravelAgencyService {

    List<TravelAgency> findAllTravelAgency();

    Optional<TravelAgency> findTravelAgencyByName(String name);

    String addTravelAgency(TravelAgencyDto travelAgencyDto);

    String updateTravelAgency(Long travelAgency_id , TravelAgencyDto travelAgencyDto);

    String deleteTravelAgency(Long travelAgency_id);
}
