package com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.NaturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Repository.NaturalAttractionRepository;
import com.ILSI.TouristeProject.Locations.Attraction.Service.INaturalAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.model.NaturalAttraction;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class NaturalAttractionService implements INaturalAttractionService {

    private final NaturalAttractionRepository naturalAttractionRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<NaturalAttraction> getAllNaturalAttraction() {return naturalAttractionRepository.findAll();}

    @Override
    public Optional<NaturalAttraction> findNaturalAttractionByName(String name) {
        return naturalAttractionRepository.findNaturalAttractionByName(name);
    }

    @Override
    public String addNaturalAttraction(NaturalAttractionDto naturalAttractionDto) throws IOException {
        Optional<NaturalAttraction> attraction = naturalAttractionRepository.findNaturalAttractionByName(naturalAttractionDto.getName());

        if(attraction.isPresent()){
            throw new LocationAlreadyExistException("Natural Attraction with Name "+naturalAttractionDto.getName()+"Already Exist");
        }
        var newNaturalAttraction = new NaturalAttraction();
        getNaturalAttraction(naturalAttractionDto, newNaturalAttraction);
        return "Success! Natural Attraction Created successfully";
    }

    @Override
    public String UpdateNaturalAttraction(Long naturalAttraction_id, NaturalAttractionDto naturalAttractionDto) throws IOException {
        NaturalAttraction attraction = naturalAttractionRepository.findById(naturalAttraction_id)
                .orElseThrow(()->new LocationNotFoundException("Natural Attraction with ID = "+ naturalAttraction_id+" Not Found"));
        getNaturalAttraction(naturalAttractionDto, attraction);
        return "Success! Natural Attraction Updated successfully";
    }

    @Override
    public String DeleteNaturalAttraction(Long naturalAttraction_id) {
        NaturalAttraction attraction = naturalAttractionRepository.findById(naturalAttraction_id)
                .orElseThrow(()->new LocationNotFoundException("Natural Attraction with ID = "+ naturalAttraction_id+" Not Found"));
        naturalAttractionRepository.delete(attraction);
        return "Success! Natural Attraction Deleted successfully";
    }


    private void getNaturalAttraction(NaturalAttractionDto naturalAttractionDto, NaturalAttraction newNaturalAttraction) throws IOException {
       locationImageService.getAttraction(naturalAttractionDto, newNaturalAttraction);
        newNaturalAttraction.setProtectedArea(naturalAttractionDto.isProtectedArea());
        naturalAttractionRepository.save(newNaturalAttraction);
    }
}
