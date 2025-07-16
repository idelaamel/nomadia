package com.ILSI.TouristeProject.AutreClass.Service;

import com.ILSI.TouristeProject.AutreClass.Repository.ItineraryRepository;
import com.ILSI.TouristeProject.AutreClass.Repository.VisitRepository;
import com.ILSI.TouristeProject.AutreClass.dto.ItineraryDto;
import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.AutreClass.model.Itinerary;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ItineraryService {

    private final ItineraryRepository itineraryRepository;
    private final VisitRepository visitRepository;
    private final LocationImageService locationImageService;


    public Optional<Itinerary> findItineraryByName(String name){return itineraryRepository.findItineraryByName(name);}

    public Itinerary CreateNewItinerary(ItineraryDto dto, Long visit_id){
        Itinerary itinerary = new Itinerary();
        itinerary.setName(dto.getName());
        itinerary.setDescription(dto.getDescription());
        itinerary.setStartDate(dto.getStartDate());
        itinerary.setEndDate(dto.getEndDate());
        itinerary.setVisit(visitRepository.findById(visit_id).orElseThrow(()-> new IllegalArgumentException("Visit Not  Found")));
        City departureCity = locationImageService.getOrCreateCountryAndCity(dto.getDepartureCountryName(), dto.getDepartureCityName()).getRight();
        City arrivalCity = locationImageService.getOrCreateCountryAndCity(dto.getArrivalCountryName(), dto.getArrivalCityName()).getRight();
        itinerary.setDepartureCity(departureCity);
        itinerary.setArrivalCity(arrivalCity);
        return itineraryRepository.save(itinerary);
    }


    public Itinerary updateItinerary(Long itineraryId, ItineraryDto dto, Long visit_id) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new RuntimeException("Itinerary with id: " + itineraryId + " not found"));
        itinerary.setName(dto.getName());
        itinerary.setDescription(dto.getDescription());
        itinerary.setStartDate(dto.getStartDate());
        itinerary.setEndDate(dto.getEndDate());
        itinerary.setVisit(visitRepository.findById(visit_id).orElseThrow(() -> new IllegalArgumentException("Visit Not Found")));
        City departureCity = locationImageService.getOrCreateCountryAndCity(dto.getDepartureCountryName(), dto.getDepartureCityName()).getRight();
        City arrivalCity = locationImageService.getOrCreateCountryAndCity(dto.getArrivalCountryName(), dto.getArrivalCityName()).getRight();
        itinerary.setDepartureCity(departureCity);
        itinerary.setArrivalCity(arrivalCity);
        return itineraryRepository.save(itinerary);
    }

    public void deleteItinerary(Long itineraryId) {
        Itinerary itinerary = itineraryRepository.findById(itineraryId)
                .orElseThrow(() -> new RuntimeException("Itinerary with id: " + itineraryId + " not found"));
        itineraryRepository.delete(itinerary);
    }
}
