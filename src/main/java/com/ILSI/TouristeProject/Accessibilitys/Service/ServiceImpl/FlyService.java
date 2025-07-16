package com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl;

import com.ILSI.TouristeProject.Accessibilitys.Repository.FlyRepository;
import com.ILSI.TouristeProject.Accessibilitys.Service.IFlayService;
import com.ILSI.TouristeProject.Accessibilitys.dto.FlyDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FlyService implements IFlayService {

     private final FlyRepository flyRepository;
     private final LocationImageService locationImageService;

    @Override
    public List<Fly> getAllFly() {return flyRepository.findAll();}

    @Override
    public Optional<Fly> findFlyByName(String name) {return flyRepository.findByName(name);}

    @Override
    public String addFly(FlyDto flyDto) throws IOException {
        Optional<Fly> fly = flyRepository.findByName(flyDto.getName());
        if(fly.isPresent()) {
            throw new AccessibilityAlreadyExistException("Fly with Name "+flyDto.getName()+" Already Exist");
        }
        Fly newFly = new Fly();
        getFly(flyDto, newFly);
        return "Success! Fly Created Successfully";
    }

    @Override
    public String updateFly(Long fly_id, FlyDto flyDto) throws IOException {
        Fly fly = flyRepository.findById(fly_id).orElseThrow(()-> new AccessibilityNotFoundException("Fly with ID = "+ fly_id +" Not Found"));
        getFly(flyDto, fly);
        return "Success! Fly Updated Successfully";
    }

    @Override
    public String deleteFly(Long fly_id) {
        Fly fly = flyRepository.findById(fly_id).orElseThrow(()-> new AccessibilityNotFoundException("Fly with ID = "+ fly_id +" Not Found"));
        flyRepository.delete(fly);
        return "Success! Fly deleted Successfully";
    }

    private void getFly(FlyDto flyDto, Fly newFly) throws IOException {
        locationImageService.getAccessibility(flyDto, newFly);
        newFly.setAirline(flyDto.getAirline());
        newFly.setArrivalAirport(flyDto.getArrivalAirport());
        newFly.setDepartureAirport(flyDto.getDepartureAirport());
        flyRepository.save(newFly);
    }
}
