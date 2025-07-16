package com.ILSI.TouristeProject.RecommanderSystem.Controller;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import com.ILSI.TouristeProject.Accessibilitys.Controller.PublicAccessibilityController;
import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Activitis.ActivityDto;
import com.ILSI.TouristeProject.Activitis.Controller.PublicActivityController;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceDto;
import com.ILSI.TouristeProject.AncillaryServices.Controller.PublicAncillaryServiceController;
import com.ILSI.TouristeProject.AvailablePackages.Controller.AvailablePackagesController;
import com.ILSI.TouristeProject.AvailablePackages.dto.AvailablePackagesDto;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import com.ILSI.TouristeProject.Locations.Amenity.Controller.PublicAmenityController;
import com.ILSI.TouristeProject.Locations.Attraction.Attraction;
import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Controller.PublicAttractionController;
import com.ILSI.TouristeProject.Locations.Dto.LocationDto;
import com.ILSI.TouristeProject.Locations.model.Location;
import com.ILSI.TouristeProject.RecommanderSystem.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public/recommended/")
public class RecommendationController {

    private final AccessibilityRecommendationService accessibilityRecommendationService;
    private final ActivityRecommendationService activityRecommendationService;
    private final AncillaryServiceRecommendationService ancillaryServiceRecommendationService;
    private final AvailablePackageRecommendationService availablePackageRecommendationService;
    private final LocationRecommendationService locationRecommendationService;

    private final PublicAccessibilityController AccController;
    private final PublicActivityController acController;
    private final PublicAncillaryServiceController ancController;
    private final AvailablePackagesController avController;
    private final PublicAmenityController amenityController;
    private final PublicAttractionController attractionController;

    @GetMapping("/Accessibility/{userId}")
    public List<AccessibilityDto.AccessibilityDtoResponse> getRecommendedAccessibility(@PathVariable Long userId) throws Exception {
        List<Accessibility> accessibilityList = accessibilityRecommendationService.recommenderAccessibility(userId);
        return accessibilityList.stream()
                .map(this::convertAccessibilityToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/Activity/{userId}")
    public List<ActivityDto.ActivityDtoResponse> getRecommendedActivity(@PathVariable Long userId) throws Exception {
        List<Activity> activityList = activityRecommendationService.recommenderActivity(userId);
            return activityList.stream()
                .map(this::convertActivityToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/AncillaryService/{userId}")
    public List<AncillaryServiceDto.AncillaryServiceDtoResponse> getRecommendedAncillaryService(@PathVariable Long userId) throws Exception {
        List<AncillaryService> ancillaryServiceList= ancillaryServiceRecommendationService.recommenderAncillaryService(userId);
        return ancillaryServiceList.stream()
                .map(this::convertAncillaryServiceToDto)
                .collect(Collectors.toList());
    }
    @GetMapping("/AvailablePackage/{userId}")
    public List<AvailablePackagesDto.AvailablePackagesDtoResponse> getRecommendedAvailablePackage(@PathVariable Long userId) throws Exception {
        List<AvailablePackage> availablePackageList= availablePackageRecommendationService.recommenderAvailablePackage(userId);
        return availablePackageList.stream()
                .map(this::convertAvailablePackageToDto)
                .collect(Collectors.toList());
    }

    @GetMapping("/Location/{userId}")
    public List<LocationDto.LocationDtoResponse> getRecommendedLocation(@PathVariable Long userId) throws Exception {
        List<Location> locationList = locationRecommendationService.recommenderLocation(userId);
        return locationList.stream()
                .map(this::convertLocationToDto)
                .collect(Collectors.toList());
    }


    private AccessibilityDto.AccessibilityDtoResponse convertAccessibilityToDto(Accessibility accessibility) {
        AccessibilityDto.AccessibilityDtoResponse response = new AccessibilityDto.AccessibilityDtoResponse();
        AccController.SetAccessibilityFields(accessibility,response);
        return response;
    }
    private ActivityDto.ActivityDtoResponse convertActivityToDto(Activity activity) {
        ActivityDto.ActivityDtoResponse response = new ActivityDto.ActivityDtoResponse();
        acController.SetActivityFields(activity,response);
        return response;
    }
    private AncillaryServiceDto.AncillaryServiceDtoResponse convertAncillaryServiceToDto(AncillaryService ancillaryService) {
        AncillaryServiceDto.AncillaryServiceDtoResponse response = new AncillaryServiceDto.AncillaryServiceDtoResponse();
        ancController.SetAncillaryServiceFields(ancillaryService,response);
        return response;
    }
    private AvailablePackagesDto.AvailablePackagesDtoResponse convertAvailablePackageToDto(AvailablePackage availablePackage) {
        AvailablePackagesDto.AvailablePackagesDtoResponse response;
        response = avController.convertAvailablePackagesToDTO(availablePackage);
        return response;
    }

    private LocationDto.LocationDtoResponse convertLocationToDto(Location location) {
        if (location instanceof Amenity) {
            return amenityController.SetAmenityFields((Amenity) location, new AmenityDto.AmenityDtoResponse());
        } else if (location instanceof Attraction) {
            return attractionController.SetAttractionFields((Attraction) location, new AttractionDto.AttractionDtoResponse());
        } else {
            throw new IllegalArgumentException("Type de Location non pris en charge : " + location.getClass().getName());
        }
    }
}
