package com.ILSI.TouristeProject.Locations.Attraction.Controller;


import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.Amenity.Controller.PublicAmenityController;
import com.ILSI.TouristeProject.Locations.Amenity.Model.*;
import com.ILSI.TouristeProject.Locations.Attraction.Attraction;
import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.AttractionRepository;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.*;
import com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl.*;
import com.ILSI.TouristeProject.Locations.Attraction.model.*;
import com.ILSI.TouristeProject.Locations.model.LocationRepository;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@Tag(name = " Explore All Attraction By Users Her ")
public class PublicAttractionController {


    private final ArtificialAttractionService artificialAttractionService;
    private final CulturalAttractionService culturalAttractionService;
    private final HistoricalAttractionService historicalAttractionService;
    private final NaturalAttractionService naturalAttractionService;
    private final AttractionRepository attractionRepository;
    private final PublicAmenityController controller;
    private final LocationRepository repository;


    @GetMapping("/getLocationById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return repository.findById(id).map(location-> {
                    if (location instanceof Cafe) {return controller.convertCafeToDTO((Cafe) location);}
                    else if (location instanceof Camping) {return controller.convertCampingToDTO((Camping) location);}
                    else if (location instanceof GuestHouse) {return controller.convertGuestHouseToDTO((GuestHouse) location);}
                    else if (location instanceof Hotel) {return controller.convertHotelToDTO((Hotel) location);}
                    else if (location instanceof Lodge) {return controller.convertLodgeToDTO((Lodge) location);}
                    else if (location instanceof Restaurant) {return controller.convertRestaurantToDTO((Restaurant) location);}
                    else if (location instanceof ArtificialAttraction) {return this.convertArtificialAttractionToDTO((ArtificialAttraction) location);}
                    else if (location instanceof CulturalAttraction) {return this.convertCulturalAttractionToDTO((CulturalAttraction) location);}
                    else if (location instanceof HistoricalAttraction) {return this.convertHistoricalAttractionToDTO((HistoricalAttraction) location);}
                    else {return this.convertNaturalAttractionToDTO((NaturalAttraction) location);}
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll/Attraction")
    public List<AttractionDto.AttractionDtoResponse> getAttractions() {
        List<Attraction> listAttraction = attractionRepository.findAll();
        List<AttractionDto.AttractionDtoResponse> responseList = new ArrayList<>();
        for (Attraction attraction : listAttraction) {
            responseList.add(this.SetAttractionFields(attraction, new AttractionDto.AttractionDtoResponse()));
        }
        return responseList;
    }
    @GetMapping("/ArtificialAttractions")
    public List<ArtificialAttractionDto.ArtificialAttractionDtoResponse> getArtificialAttractions()
    {return artificialAttractionService.getAllArtificialAttractions().stream().map(this::convertArtificialAttractionToDTO).toList();}

    @GetMapping("/CulturalAttractions")
    public List<CulturalAttractionDto.CulturalAttractionDtoResponse> getCulturalAttractions(){return culturalAttractionService.getAllCulturalAttraction().stream().map(this::convertCulturalAttractionToDTO).toList();}

    @GetMapping("/HistoricalAttractions")
    public List<HistoricalAttractionDto.HistoricalAttractionDtoResponse> getHistoricalAttractions(){return historicalAttractionService.getAllHistoricalAttraction().stream().map(this::convertHistoricalAttractionToDTO).toList();}

    @GetMapping("/NaturalAttractions")
    public List<NaturalAttractionDto.NaturalAttractionDtoResponse> getNaturalAttractions(){return naturalAttractionService.getAllNaturalAttraction().stream().map(this::convertNaturalAttractionToDTO).toList();}


    public NaturalAttractionDto.NaturalAttractionDtoResponse convertNaturalAttractionToDTO(NaturalAttraction naturalAttraction) {
        NaturalAttractionDto.NaturalAttractionDtoResponse naturalAttractionDtoResponse = new NaturalAttractionDto.NaturalAttractionDtoResponse();
        this.SetAttractionFields(naturalAttraction, naturalAttractionDtoResponse);
        naturalAttractionDtoResponse.setProtectedArea(naturalAttraction.isProtectedArea());
        return naturalAttractionDtoResponse;
    }

    public ArtificialAttractionDto.ArtificialAttractionDtoResponse convertArtificialAttractionToDTO(ArtificialAttraction artificialAttraction) {
        ArtificialAttractionDto.ArtificialAttractionDtoResponse artificialAttractionDtoResponse= new ArtificialAttractionDto.ArtificialAttractionDtoResponse();
        this.SetAttractionFields(artificialAttraction, artificialAttractionDtoResponse);
        artificialAttractionDtoResponse.setYearBuild(artificialAttraction.getYearBuild());
        return artificialAttractionDtoResponse;
    }

    public CulturalAttractionDto.CulturalAttractionDtoResponse convertCulturalAttractionToDTO(CulturalAttraction culturalAttraction) {
        CulturalAttractionDto.CulturalAttractionDtoResponse response = new CulturalAttractionDto.CulturalAttractionDtoResponse();
        this.SetAttractionFields(culturalAttraction, response);
        response.setYearBuild(culturalAttraction.getYearBuild());
        response.setStyle(culturalAttraction.getStyle());
        return response;
    }

    public HistoricalAttractionDto.HistoricalAttractionDtoResponse convertHistoricalAttractionToDTO(HistoricalAttraction attraction) {
        HistoricalAttractionDto.HistoricalAttractionDtoResponse response = new HistoricalAttractionDto.HistoricalAttractionDtoResponse();
        this.SetAttractionFields(attraction, response);
        response.setStyle(attraction.getStyle());
        return response;
    }

    public AttractionDto.AttractionDtoResponse SetAttractionFields(Attraction source, AttractionDto.AttractionDtoResponse target) {
        List<String> urls = source.getImages().stream().map(Image::getImageUrl).toList();
        target.setId_Location(source.getId_Location());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setImageUrls(urls);
        target.setEntryFre(source.getEntryFre());
        target.setGuideToursAvailable(source.isGuideToursAvailable());
        target.setCityName(source.getCity().getName());
        target.setCountryName(source.getCity().getCountry().getName());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
        return target;
    }
}
