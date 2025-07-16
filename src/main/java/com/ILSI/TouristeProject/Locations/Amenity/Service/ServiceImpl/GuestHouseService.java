package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.CampingDto;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.GuestHouseDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Camping;
import com.ILSI.TouristeProject.Locations.Amenity.Model.GuestHouse;
import com.ILSI.TouristeProject.Locations.Amenity.Repository.GuestHouseRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Service.IGuestHouseService;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GuestHouseService implements IGuestHouseService {

    private final GuestHouseRepository guestHouseRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<GuestHouse> getAllGuestHouse() {return guestHouseRepository.findAll();}

    @Override
    public Optional<GuestHouse> findGuestHouseByName(String name) {return guestHouseRepository.findByName(name);}

    @Override
    public String addGuestHouse(GuestHouseDto guestHouseRequest) throws IOException {
        Optional<GuestHouse> guestHouse = this.findGuestHouseByName(guestHouseRequest.getName());
        if(guestHouse.isPresent()){
            throw new LocationAlreadyExistException("GuestHouse with Name "+guestHouseRequest.getName()+" Already Exist");
        }
        var newGustHouse = new GuestHouse();
        getGuestHouse(guestHouseRequest, newGustHouse);
        return "Success! GuestHouse Created successfully";
    }

    @Override
    public String UpdateGuestHouse(Long guestHouse_id, GuestHouseDto guestHouseDto) throws IOException{
        GuestHouse existingGuestHouse = guestHouseRepository.findById(guestHouse_id)
                .orElseThrow(()-> new LocationNotFoundException("GuestHouse with ID = "+guestHouse_id+" Note Found"));
      getGuestHouse(guestHouseDto, existingGuestHouse);
      return "Success! GuestHouse Updated successfully";
    }

    @Override
    public String DeleteGuestHouse(Long guestHouse_id) {
        GuestHouse existingGuestHouse = guestHouseRepository.findById(guestHouse_id)
                .orElseThrow(()-> new LocationNotFoundException("GuestHouse with ID = "+guestHouse_id+" Note Found"));
        guestHouseRepository.delete(existingGuestHouse);
        return "Success! GuestHouse deleted successfully";
    }


    private void getGuestHouse(GuestHouseDto request, GuestHouse newGustHouse) throws IOException {
        locationImageService.getAmenity(request, newGustHouse);
        newGustHouse.setNumberRooms(request.getNumberRooms());
        newGustHouse.setBreakfastIncluded(request.isBreakfastIncluded());
        guestHouseRepository.save(newGustHouse);
    }



}
