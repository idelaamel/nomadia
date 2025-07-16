package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.CampingDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Camping;
import com.ILSI.TouristeProject.Locations.Amenity.Repository.CampingRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ICampingService;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@RequiredArgsConstructor
@Service
public class CampingService implements ICampingService {

    private final CampingRepository campingRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<Camping> getAllCamping() {return campingRepository.findAll();}


    @Override
    public Optional<Camping> findCampingByName(String name) {
        return campingRepository.findByName(name);
    }

    @Override
    public String addCamping(CampingDto campingRequest) throws IOException {
        Optional<Camping> camping = this.findCampingByName(campingRequest.getName());
        if(camping.isPresent()){
            throw new LocationAlreadyExistException("Camping with Name "+campingRequest.getName()+" Already Exist");
        }
        var newCamping = new Camping();
        getCamping(campingRequest, newCamping);
        return "Success! Camping Created successfully";
    }
    @Override

    public String UpdateCamping(Long camping_id, CampingDto campingDto) throws IOException {
        Camping existingCamping = campingRepository.findById(camping_id).orElseThrow(()-> new LocationNotFoundException("Camping with ID = "+camping_id+" Not Found"));
      getCamping(campingDto, existingCamping);
      return "Success! Camping Updated successfully";
    }

    @Override
    public String DeleteCamping(Long camping_id) {
        Camping existingCamping = campingRepository.findById(camping_id).orElseThrow(()-> new LocationNotFoundException("Camping with ID = "+camping_id+" Not Found"));
        campingRepository.delete(existingCamping);
        return "Success! Camping deleted successfully";
   }

    private void getCamping(CampingDto campingDto , Camping newCamping) throws IOException {
       locationImageService.getAmenity(campingDto, newCamping);
        newCamping.setCapacity(campingDto.getCapacity());
        newCamping.setElectricityAvailability(campingDto.isElectricityAvailability());
        newCamping.setHasWaterSupply(campingDto.isHasWaterSupply());
        newCamping.setSanitaryAvailability(campingDto.isSanitaryAvailability());
        campingRepository.save(newCamping);
    }
}
