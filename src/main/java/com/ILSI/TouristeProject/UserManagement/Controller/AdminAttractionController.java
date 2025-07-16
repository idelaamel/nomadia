package com.ILSI.TouristeProject.UserManagement.Controller;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.ArtificialAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.CulturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.HistoricalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.NaturalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl.ArtificialAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl.CulturalAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl.HistoricalAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl.NaturalAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.model.ArtificialAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.CulturalAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.HistoricalAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.NaturalAttraction;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.hibernate.boot.jaxb.hbm.spi.NativeQueryReturn;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name="Attractions Management By Admin", description = "Create, Update and Delete Attractions")
public class AdminAttractionController {

    private final NaturalAttractionService naturalAttractionService;
    private final CulturalAttractionService culturalAttractionService;
    private final HistoricalAttractionService historicalAttractionService;
    private final ArtificialAttractionService artificialAttractionService;

/******************************************  Create  ******************************************************/
    @PostMapping(value = "/addNaturalAttraction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addNaturalAttraction(@Valid @ModelAttribute NaturalAttractionDto naturalAttractionDto) throws IOException{
        try { return naturalAttractionService.addNaturalAttraction(naturalAttractionDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }


    @PostMapping(value = "/addHistoricalAttraction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addHistoricalAttraction(@Valid @ModelAttribute HistoricalAttractionDto historicalAttractionDto) throws IOException{
        try { return historicalAttractionService.addHistoricalAttraction(historicalAttractionDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addArtificialAttraction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addArtificialAttraction(@Valid @ModelAttribute ArtificialAttractionDto artificialAttractionDto) throws IOException{
        try { return artificialAttractionService.addArtificialAttraction(artificialAttractionDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addCulturalAttraction", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addCulturalAttraction(@Valid @ModelAttribute CulturalAttractionDto culturalAttractionDto) throws IOException{
        try { return culturalAttractionService.addCulturalAttraction(culturalAttractionDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }


/******************************************  Update  ******************************************************/
    @PutMapping(value = "/updateNaturalAttraction/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateNaturalAttraction(@PathVariable Long id, @ModelAttribute NaturalAttractionDto naturalAttractionDto) throws IOException {
        try { return naturalAttractionService.UpdateNaturalAttraction(id , naturalAttractionDto);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/updateHistoricalAttraction/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateHistoricalAttraction(@PathVariable Long id, @ModelAttribute HistoricalAttractionDto historicalAttractionDto) throws IOException{
        try { return historicalAttractionService.UpdateHistoricalAttraction(id , historicalAttractionDto);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/updateArtificialAttraction/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateArtificialAttraction(@PathVariable Long id, @ModelAttribute ArtificialAttractionDto artificialAttractionDto) throws IOException {
        try { return artificialAttractionService.UpdateArtificialAttraction(id , artificialAttractionDto);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/updateCulturalAttraction/{id}" , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateCulturalAttraction(@PathVariable Long id, @ModelAttribute CulturalAttractionDto culturalAttractionDto) throws IOException{
        try {  return culturalAttractionService.UpdateCulturalAttraction(id , culturalAttractionDto);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

/******************************************  Delete  ******************************************************/
    @DeleteMapping("/deleteNaturalAttraction/{id}")
    public String deleteNaturalAttraction(@PathVariable Long id) {
        try { return naturalAttractionService.DeleteNaturalAttraction(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteHistoricalAttraction/{id}")
    public String deleteHistoricalAttraction(@PathVariable Long id) {
        try { return historicalAttractionService.DeleteHistoricalAttraction(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteArtificialAttraction/{id}")
    public String deleteArtificialAttraction(@PathVariable Long id) {
        try { return artificialAttractionService.DeleteArtificialAttraction(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteCulturalAttraction/{id}")
    public String deleteCulturalAttraction(@PathVariable Long id) {
        try { return culturalAttractionService.DeleteCulturalAttraction(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

}
