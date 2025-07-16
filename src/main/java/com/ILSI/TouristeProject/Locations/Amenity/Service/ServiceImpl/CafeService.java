package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;


import com.ILSI.TouristeProject.AutreClass.Repository.CityRepository;
import com.ILSI.TouristeProject.AutreClass.Repository.CountryRepository;
import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.AutreClass.model.Country;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;
import com.ILSI.TouristeProject.Locations.Amenity.Repository.CafeRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ICafeService;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CafeService implements ICafeService {

    private final CafeRepository cafeRepository;
    private final LocationImageService locationImageService;


    @Override
    public List<Cafe> getAllCafes() {return cafeRepository.findAll();}

    @Override
    public Optional<Cafe> findCafeByName(String name) {
        return cafeRepository.findByName(name);
    }

    @Override
    public String addCafe(CafeDto request) throws  IOException {
        Optional<Cafe> cafe = this.findCafeByName(request.getName());
        if(cafe.isPresent()){
            throw new LocationAlreadyExistException("Cafe with Name"+request.getName()+"Already Exist");
        }
        getCafe(request, new Cafe());
        return "Success! Cafe Created successfully";
    }
    @Override
    public String UpdateCafe(Long cafe_id, CafeDto cafeDto) throws IOException{
        Cafe cafe = cafeRepository.findById(cafe_id).orElseThrow(()-> new LocationNotFoundException("Café with ID = "+ cafe_id +" Not Found"));
        getCafe(cafeDto, cafe);
        return "Success! Cafe Updated successfully";
    }
    @Override
    public String DeleteCafe(Long cafe_id) {
        Cafe cafe = cafeRepository.findById(cafe_id).orElseThrow(()-> new LocationNotFoundException("Café with ID = "+ cafe_id +" Not Found"));
        cafeRepository.delete(cafe);
        return "Success! Cafe deleted successfully";
    }

    private void getCafe(CafeDto cafeDto, Cafe newcafe) throws IOException {
        locationImageService.getAmenity(cafeDto, newcafe);
        newcafe.setMenu(cafeDto.getMenu());
        newcafe.setWifiAvailable(cafeDto.isWifiAvailable());
        cafeRepository.save(newcafe);
    }
}
