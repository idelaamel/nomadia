package com.ILSI.TouristeProject.RecommanderSystem.Controller;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityRepository;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAccessibility;
import com.ILSI.TouristeProject.RecommanderSystem.Service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
public class FavoritesController {

    private final AccessibilityRecommendationService accessibilityRecommendationService;
    private final ActivityRecommendationService activityRecommendationService;
    private final AncillaryServiceRecommendationService ancillaryServiceRecommendationService;
    private final AvailablePackageRecommendationService availablePackageRecommendationService;
    private final LocationRecommendationService locationRecommendationService;
    private final AccessibilityRepository accessibilityRepository;


    @PostMapping("/add/AccessibilityToFavorites/{id_User}/{id_Accessibility}")
    public ResponseEntity<?> addAccessibilityToFavorites(@PathVariable Long id_User, @PathVariable Long id_Accessibility) {
        try {
            accessibilityRecommendationService.addAccessibilityToFavorites(id_User, id_Accessibility);
            return ResponseEntity.ok("Accessibility added Successfully to your Favorites.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @PostMapping("/add/ActivityToFavorites/{id_User}/{id_Activity}")
    public ResponseEntity<?> addActivityToFavorites(@PathVariable Long id_User, @PathVariable Long id_Activity) {
        try {
            activityRecommendationService.addActivityToFavorites(id_User, id_Activity);
            return ResponseEntity.ok("Activity added Successfully to your Favorites.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add/AncillaryServiceToFavorites/{id_User}/{id_AncillaryService}")
    public ResponseEntity<?> addAncillaryServiceToFavorites(@PathVariable Long id_User, @PathVariable Long id_AncillaryService) {
        try {
            ancillaryServiceRecommendationService.addAncillaryServiceToFavorites(id_User, id_AncillaryService);
            return ResponseEntity.ok("Ancillary Service added Successfully to your Favorites.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add/AvailablePackageToFavorites/{id_User}/{id_AvailablePackage}")
    public ResponseEntity<?> addAvailablePackageToFavorites(@PathVariable Long id_User, @PathVariable Long id_AvailablePackage) {
        try {
            availablePackageRecommendationService.addAvailablePackageToFavorites(id_User, id_AvailablePackage);
            return ResponseEntity.ok("Available Package added Successfully to your Favorites.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/add/LocationToFavorites/{id_User}/{id_Location}")
    public ResponseEntity<?> addLocationToFavorites(@PathVariable Long id_User, @PathVariable Long id_Location) {
        try {
            locationRecommendationService.addLocationToFavorites(id_User, id_Location);
            return ResponseEntity.ok("Location added Successfully to your Favorites.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @GetMapping("/show/favoritesAccessibility/{id_user}")
    public ResponseEntity<?> getFavoritesAccessibility(@PathVariable("id_user") Long userId) {
            List<?> result  = accessibilityRecommendationService.getFavoritesAccessibilityByUser(userId);
            return ResponseEntity.ok(result);
    }

    @GetMapping("/show/favoritesActivity/{id_user}")
    public ResponseEntity<?> getFavoritesActivity(@PathVariable("id_user") Long userId) {
        List<?> result  = activityRecommendationService.getFavoritesActivityByUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/show/favoritesAncillaryService/{id_user}")
    public ResponseEntity<?> getFavoritesAncillaryService(@PathVariable("id_user") Long userId) {
        List<?> result  = ancillaryServiceRecommendationService.getFavoritesAncillaryServiceByUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/show/favoritesAvailablePackage/{id_user}")
    public ResponseEntity<?> getFavoritesAvailablePackage(@PathVariable("id_user") Long userId) {
        List<?> result  = availablePackageRecommendationService.getFavoritesAvailablePackageByUser(userId);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/show/favoritesLocation/{id_user}")
    public ResponseEntity<?> getFavoritesLocation(@PathVariable("id_user") Long userId) {
        List<?> result  = locationRecommendationService.getFavoritesLocationByUser(userId);
        return ResponseEntity.ok(result);
    }


}
