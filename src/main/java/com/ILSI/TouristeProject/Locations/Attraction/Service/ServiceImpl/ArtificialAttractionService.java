package com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.HotelDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Hotel;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.ArtificialAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.CulturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Repository.ArtificialAttractionRepository;
import com.ILSI.TouristeProject.Locations.Attraction.Service.IArtificialAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.model.ArtificialAttraction;
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
public class ArtificialAttractionService implements IArtificialAttractionService {

    private final ArtificialAttractionRepository artificialAttractionRepository;
    private final LocationImageService locationImageService;


    @Override
    public List<ArtificialAttraction> getAllArtificialAttractions() {return artificialAttractionRepository.findAll();}

    @Override
    public Optional<ArtificialAttraction> findArtificialAttractionByName(String name) {
        return artificialAttractionRepository.findArtificialAttractionByName(name);
    }

    @Override
    public String addArtificialAttraction(ArtificialAttractionDto artificialAttractionDto) throws IOException{
        Optional<ArtificialAttraction> att = this.findArtificialAttractionByName(artificialAttractionDto.getName());
        if(att.isPresent()){
            throw new LocationAlreadyExistException("Artificial Attraction with Name "+artificialAttractionDto.getName()+"Already Exist");
        }
        var newArtificialAttraction = new ArtificialAttraction();
        getArtificialAttraction(artificialAttractionDto, newArtificialAttraction);
        return "Success! Artificial Attraction Created successfully";
    }

    @Override
    public String UpdateArtificialAttraction(Long artificialAttraction_id, ArtificialAttractionDto artificialAttractionDto) throws IOException{
        ArtificialAttraction attraction = artificialAttractionRepository.findById(artificialAttraction_id)
                .orElseThrow(()-> new LocationNotFoundException("Artificial Attraction with ID = "+ artificialAttraction_id+" Not Found"));
        getArtificialAttraction(artificialAttractionDto, attraction);
        return "Success! Artificial Attraction Updated successfully";
    }

    @Override
    public String DeleteArtificialAttraction(Long artificialAttraction_id) {
        ArtificialAttraction attraction = artificialAttractionRepository.findById(artificialAttraction_id)
                .orElseThrow(()-> new LocationNotFoundException("Artificial Attraction with ID = "+ artificialAttraction_id+" Not Found"));
        artificialAttractionRepository.delete(attraction);
       return "Success! Artificial Attraction Deleted successfully";
    }

    private void getArtificialAttraction( ArtificialAttractionDto artificialAttractionDto, ArtificialAttraction newArtificialAttraction) throws IOException {
        locationImageService.getAttraction(artificialAttractionDto, newArtificialAttraction);
        newArtificialAttraction.setYearBuild(artificialAttractionDto.getYearBuild());
        artificialAttractionRepository.save(newArtificialAttraction);
    }

}
