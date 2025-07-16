package com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.CulturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Repository.CulturalAttractionRepository;
import com.ILSI.TouristeProject.Locations.Attraction.Service.ICulturalAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.model.CulturalAttraction;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CulturalAttractionService implements ICulturalAttractionService {

    private final CulturalAttractionRepository culturalAttractionRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<CulturalAttraction> getAllCulturalAttraction() {
        return culturalAttractionRepository.findAll();
    }

    @Override
    public Optional<CulturalAttraction> findCulturalAttractionByName(String name) {return culturalAttractionRepository.findCulturalAttractionByName(name);}

    @Override
    public String addCulturalAttraction(CulturalAttractionDto culturalAttractionDto) throws IOException{
        Optional<CulturalAttraction> attraction= this.findCulturalAttractionByName(culturalAttractionDto.getName());
        if(attraction.isPresent()){
            throw new LocationAlreadyExistException("Cultural Attraction with Name "+culturalAttractionDto.getName()+"Already Exist");
        }
        var newCulturalAttraction = new CulturalAttraction();
        getCulturalAttraction(culturalAttractionDto, newCulturalAttraction);
        return "Success! Cultural Attraction Created successfully";
    }



    @Override
    public String UpdateCulturalAttraction(Long culturalAttraction_id, CulturalAttractionDto culturalAttractionDto) throws IOException{
        CulturalAttraction attraction = culturalAttractionRepository.findById(culturalAttraction_id)
                .orElseThrow(()-> new LocationNotFoundException("Cultural Attraction with ID ="+ culturalAttraction_id+" Not Found"));
        getCulturalAttraction(culturalAttractionDto, attraction);
        return "Success! Cultural Attraction Updated successfully";
    }

    @Override
    public String DeleteCulturalAttraction(Long culturalAttraction_id) {
        CulturalAttraction attraction = culturalAttractionRepository.findById(culturalAttraction_id)
                .orElseThrow(()-> new LocationNotFoundException("Cultural Attraction with ID ="+ culturalAttraction_id+" Not Found"));
        culturalAttractionRepository.delete(attraction);
        return "Success! Cultural Attraction Updated successfully";
    }

    private void getCulturalAttraction(CulturalAttractionDto culturalAttractionDto , CulturalAttraction newCulturalAttraction) throws IOException {
        locationImageService.getAttraction(culturalAttractionDto, newCulturalAttraction);
        newCulturalAttraction.setStyle(culturalAttractionDto.getStyle());
        newCulturalAttraction.setYearBuild(culturalAttractionDto.getYearBuild());
        culturalAttractionRepository.save(newCulturalAttraction);
    }
}
