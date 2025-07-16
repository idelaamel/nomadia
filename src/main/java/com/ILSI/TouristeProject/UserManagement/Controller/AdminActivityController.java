package com.ILSI.TouristeProject.UserManagement.Controller;

import com.ILSI.TouristeProject.Activitis.Dto.AdventureActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.CulturalActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.SportiveActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.TraditionalActivityDto;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.AdventureActivityService;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.CulturalActivityService;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.SportiveActivityService;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.TraditionalActivityService;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name="Activity Management By Admin", description = "Create, Update and Delete Activity")
public class AdminActivityController {


    private final AdventureActivityService adventureActivityService;
    private final CulturalActivityService culturalActivityService;
    private final SportiveActivityService sportiveActivityService;
    private final TraditionalActivityService traditionalActivityService;


/******************************************  Create  ******************************************************/
    @PostMapping(value = "/add/AdventureActivity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addAdventureActivity(@Valid @ModelAttribute AdventureActivityDto activityDto) throws IOException {
        try { return adventureActivityService.addAdventureActivity(activityDto); }
        catch (ActivityAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/add/CulturalActivity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addCulturalActivity(@Valid @ModelAttribute CulturalActivityDto activityDto) throws IOException {
        try { return culturalActivityService.addCulturalActivity(activityDto); }
        catch (ActivityAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/add/SportiveActivity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addSportiveActivity(@Valid @ModelAttribute SportiveActivityDto activityDto) throws IOException {
        try { return sportiveActivityService.addSportiveActivity(activityDto); }
        catch (ActivityAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/add/TraditionalActivity", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addTraditionalActivity(@Valid @ModelAttribute TraditionalActivityDto activityDto) throws IOException {
        try { return traditionalActivityService.addTraditionalActivity(activityDto); }
        catch (ActivityAlreadyExistException e){return e.getMessage();}
    }

/******************************************  Update  ******************************************************/
    @PutMapping(value = "/update/AdventureActivity/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateAdventureActivity(@PathVariable Long id, @ModelAttribute AdventureActivityDto activityDto) throws IOException{
        try { return adventureActivityService.updateAdventureActivity(id, activityDto); }
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/update/CulturalActivity/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateCulturalActivity(@PathVariable Long id, @ModelAttribute CulturalActivityDto activityDto) throws IOException{
        try { return culturalActivityService.updateCulturalActivity(id, activityDto); }
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/update/SportiveActivity/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateSportiveActivity(@PathVariable Long id, @ModelAttribute SportiveActivityDto activityDto) throws IOException{
        try { return sportiveActivityService.updateSportiveActivity(id, activityDto);}
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/update/TraditionalActivity/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateTraditionalActivity(@PathVariable Long id, @ModelAttribute TraditionalActivityDto activityDto) throws IOException{
        try { return traditionalActivityService.updateTraditionalActivity(id, activityDto);}
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }

/******************************************  Delete  ******************************************************/
    @DeleteMapping("/delete/AdventureActivity/{id}")
    public String deleteAdventureActivity(@PathVariable Long id) {
        try { return adventureActivityService.deleteAdventureActivity(id); }
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }
    @DeleteMapping("/delete/CulturalActivity/{id}")
    public String deleteCulturalActivity(@PathVariable Long id) {
        try { return culturalActivityService.deleteCulturalActivity(id); }
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }
    @DeleteMapping("/delete/SportiveActivity/{id}")
    public String deleteSportiveActivity(@PathVariable Long id) {
        try { return sportiveActivityService.deleteSportiveActivity(id); }
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }
    @DeleteMapping("/delete/TraditionalActivity/{id}")
    public String deleteTraditionalActivity(@PathVariable Long id) {
        try { return traditionalActivityService.deleteTraditionalActivity(id); }
        catch (ActivityNotFoundException e) {return e.getMessage();}
    }

}
