package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.Repository.TravelAgencyRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.ITravelAgencyService;
import com.ILSI.TouristeProject.AncillaryServices.dto.TravelAgencyDto;
import com.ILSI.TouristeProject.AncillaryServices.model.TravelAgency;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TravelAgencyService implements ITravelAgencyService {

    private final TravelAgencyRepository  travelAgencyRepository;
    private final AncillaryServicesService ancillaryServicesService;

    @Override
    public List<TravelAgency> findAllTravelAgency() {return travelAgencyRepository.findAll();}

    @Override
    public Optional<TravelAgency> findTravelAgencyByName(String name) {
        return Optional.empty();
    }

    @Override
    public String addTravelAgency(TravelAgencyDto travelAgencyDto) {
        Optional<TravelAgency> existingTravelAgency = travelAgencyRepository.findTravelAgencyByName(travelAgencyDto.getName());
        if (existingTravelAgency.isPresent()) {
            throw new AncillaryServiceAlreadyExistException("Travel Agency with Name "+travelAgencyDto.getName()+" Already Exist");
        }
        this.getTravelAgency(travelAgencyDto, new TravelAgency());
        return "Success! Travel Agency Created successfully";
    }

    @Override
    public String updateTravelAgency(Long travelAgency_id, TravelAgencyDto travelAgencyDto) {
        TravelAgency existingTravelAgency = travelAgencyRepository.findById(travelAgency_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Travel Agency with ID = "+travelAgency_id+" Not Found"));
        this.getTravelAgency(travelAgencyDto, existingTravelAgency);
        return "Success! Travel Agency Updated successfully";
    }

    @Override
    public String deleteTravelAgency(Long travelAgency_id) {
        TravelAgency existingTravelAgency = travelAgencyRepository.findById(travelAgency_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Travel Agency with ID = "+travelAgency_id+" Not Found"));
        travelAgencyRepository.delete(existingTravelAgency);
        return "Success! Travel Agency Deleted successfully";
    }


    private void getTravelAgency(TravelAgencyDto travelAgencyDto, TravelAgency newTravelAgency) {
        ancillaryServicesService.getAncillaryService(travelAgencyDto, newTravelAgency);
        newTravelAgency.setWebSiteUrl(travelAgencyDto.getWebSiteUrl());
        travelAgencyRepository.save(newTravelAgency);
    }
}
