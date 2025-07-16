package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.Repository.TourGuideRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.ITourGuidService;
import com.ILSI.TouristeProject.AncillaryServices.dto.TourGuideDto;
import com.ILSI.TouristeProject.AncillaryServices.model.TourGuide;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TourGuidService implements ITourGuidService {

    private final TourGuideRepository tourGuideRepository;
    private final AncillaryServicesService ancillaryServicesService;

    @Override
    public List<TourGuide> findAllTourGuides() {return tourGuideRepository.findAll();}

    @Override
    public Optional<TourGuide> findTourGuidByName(String name) {return tourGuideRepository.findTourGuideByName(name);}

    @Override
    public String addTourGuide(TourGuideDto tourGuideDto) {
        Optional<TourGuide> existingTourGuide = tourGuideRepository.findTourGuideByName(tourGuideDto.getName());
        if(existingTourGuide.isPresent()){
            throw new AncillaryServiceAlreadyExistException("TourGuide Service with Name "+tourGuideDto.getName()+" Already Exist");
        }
        this.getTourGuideService(tourGuideDto, new TourGuide());
        return "Success! TourGuide Service Created successfully";
    }

    @Override
    public String updateTourGuide(Long tourGuide_id, TourGuideDto tourGuideDto) {
        TourGuide existingTourGuide = tourGuideRepository.findById(tourGuide_id).
                orElseThrow( ()-> new AncillaryServiceAlreadyExistException("TourGuide Service with ID = "+tourGuide_id+" Not Found"));
        this.getTourGuideService(tourGuideDto, existingTourGuide);
        return "Success! TourGuide Service Updated successfully";
    }

    @Override
    public String deleteTourGuide(Long tourGuide_id) {
        TourGuide existingTourGuide = tourGuideRepository.findById(tourGuide_id).
                orElseThrow( ()-> new AncillaryServiceAlreadyExistException("TourGuide Service with ID = "+tourGuide_id+" Not Found"));
        tourGuideRepository.delete(existingTourGuide);
        return "Success! TourGuide Service Deleted successfully";
    }


    private void getTourGuideService(TourGuideDto tourGuideDto, TourGuide newTourGuide) {
        ancillaryServicesService.getAncillaryService(tourGuideDto, newTourGuide);
        newTourGuide.setGroupSizeLimit(tourGuideDto.getGroupSizeLimit());
        newTourGuide.setLanguageSupported(tourGuideDto.getLanguageSupported());
        tourGuideRepository.save(newTourGuide);

    }
}
