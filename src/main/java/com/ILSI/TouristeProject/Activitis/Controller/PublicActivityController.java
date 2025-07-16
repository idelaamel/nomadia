package com.ILSI.TouristeProject.Activitis.Controller;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Activitis.ActivityDto;
import com.ILSI.TouristeProject.Activitis.ActivityRepository;
import com.ILSI.TouristeProject.Activitis.Dto.AdventureActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.CulturalActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.SportiveActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.TraditionalActivityDto;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.AdventureActivityService;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.CulturalActivityService;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.SportiveActivityService;
import com.ILSI.TouristeProject.Activitis.Service.ServiceImpl.TraditionalActivityService;
import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;
import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;
import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;
import com.ILSI.TouristeProject.Activitis.model.TraditionalActivity;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.HotelDto;
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
@Tag(name = " Explore All Activity By Users Her ")
public class PublicActivityController {

   private final AdventureActivityService adventureActivityService;
   private final CulturalActivityService culturalActivityService;
   private final SportiveActivityService sportiveActivityService;
   private final TraditionalActivityService traditionalActivityService;
   private final ActivityRepository activityRepository;

    @GetMapping("/getActivityById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return activityRepository.findById(id).map(activity -> {
                    if (activity instanceof AdventureActivity) {return this.convertAdventureActivityToDTO((AdventureActivity) activity);}
                    else if (activity instanceof CulturalActivity) {return this.convertCulturalActivityToDTO((CulturalActivity) activity);}
                    else if (activity instanceof SportiveActivity) {return this.convertSportiveActivityToDTO((SportiveActivity) activity);}
                    else {return this.convertTraditionalActivityToDTO((TraditionalActivity) activity);}
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll/Activities")
    public List<ActivityDto.ActivityDtoResponse> getActivities() {
        List<Activity> listActivity = activityRepository.findAll();
        List<ActivityDto.ActivityDtoResponse> responseList = new ArrayList<>();
        for (Activity activity : listActivity) {
            responseList.add(this.SetActivityFields(activity, new ActivityDto.ActivityDtoResponse()));
        }
        return responseList;
    }

    @GetMapping("/Activity/Adventure")
    public List<AdventureActivityDto.AdventureActivityDtoResponse> getAdventureActivity() {return adventureActivityService.getAllAdventureActivity().stream().map(this::convertAdventureActivityToDTO).toList();}

    @GetMapping("/Activity/Cultural")
    public List<CulturalActivityDto.CulturalActivityDtoResponse> getCulturalActivity() {return culturalActivityService.getAllCulturalActivity().stream().map(this::convertCulturalActivityToDTO).toList();}

    @GetMapping("/Activity/Sportive")
    public List<SportiveActivityDto.SportiveActivityDtoResponse> getSportiveActivity() {return sportiveActivityService.getAllSportiveActivity().stream().map(this::convertSportiveActivityToDTO).toList();}

    @GetMapping("/Activity/Traditional")
    public List<TraditionalActivityDto.TraditionalActivityDtoResponse> getATraditionalActivity() {return traditionalActivityService.getAllTraditionalActivity().stream().map(this::convertTraditionalActivityToDTO).toList();}



    public AdventureActivityDto.AdventureActivityDtoResponse convertAdventureActivityToDTO(AdventureActivity activity) {
        AdventureActivityDto.AdventureActivityDtoResponse activityDtoResponse = new AdventureActivityDto.AdventureActivityDtoResponse();
        this.SetActivityFields(activity,activityDtoResponse);
        activityDtoResponse.setTerrainType(activity.getTerrainType());
        activityDtoResponse.setAgeRestriction(activity.getAgeRestriction());
        return activityDtoResponse;
    }
    public CulturalActivityDto.CulturalActivityDtoResponse convertCulturalActivityToDTO(CulturalActivity activity) {
        CulturalActivityDto.CulturalActivityDtoResponse activityDtoResponse = new CulturalActivityDto.CulturalActivityDtoResponse();
        this.SetActivityFields(activity,activityDtoResponse);
        activityDtoResponse.setTraditionAssociated(activity.getTraditionAssociated());
        return activityDtoResponse;
    }
    public SportiveActivityDto.SportiveActivityDtoResponse convertSportiveActivityToDTO(SportiveActivity activity) {
        SportiveActivityDto.SportiveActivityDtoResponse activityDtoResponse = new SportiveActivityDto.SportiveActivityDtoResponse();
        this.SetActivityFields(activity,activityDtoResponse);
        activityDtoResponse.setTypeSport(activity.getTypeSport());
        return activityDtoResponse;
    }
    public TraditionalActivityDto.TraditionalActivityDtoResponse convertTraditionalActivityToDTO(TraditionalActivity activity) {
        TraditionalActivityDto.TraditionalActivityDtoResponse activityDtoResponse = new TraditionalActivityDto.TraditionalActivityDtoResponse();
        this.SetActivityFields(activity,activityDtoResponse);
        activityDtoResponse.setCraftType(activity.getCraftType());
        return activityDtoResponse;
    }
    public ActivityDto.ActivityDtoResponse SetActivityFields(Activity source, ActivityDto.ActivityDtoResponse target) {
        List<String> urls = source.getImages().stream().map(Image::getImageUrl).toList();
        target.setId_Activity(source.getId_Activity());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setAgeLimit(source.getAgeLimit());
        target.setDuration(source.getDuration());
        target.setPrice(source.getPrice());
        target.setImagesUrls(urls);
        return target;
    }
}
