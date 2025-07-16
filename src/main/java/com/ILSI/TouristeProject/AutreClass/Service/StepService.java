package com.ILSI.TouristeProject.AutreClass.Service;

import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Activitis.ActivityRepository;
import com.ILSI.TouristeProject.AutreClass.Repository.ItineraryRepository;
import com.ILSI.TouristeProject.AutreClass.Repository.StepRepository;
import com.ILSI.TouristeProject.AutreClass.dto.StepDto;
import com.ILSI.TouristeProject.AutreClass.model.Itinerary;
import com.ILSI.TouristeProject.AutreClass.model.Step;
import com.ILSI.TouristeProject.Locations.model.Location;
import com.ILSI.TouristeProject.Locations.model.LocationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class StepService {

    private final StepRepository stepRepository;
    private final ItineraryRepository itineraryRepository;
    private final ActivityRepository activityRepository;
    private final LocationRepository locationRepository;

    public Optional<Step> findStepByTitle(String title){return stepRepository.findStepByTitle(title);}

    public Step CreateNewStep(StepDto stepDto, Long itinerary_Id) {
        Itinerary itinerary = itineraryRepository.findById(itinerary_Id)
                .orElseThrow(() -> new IllegalArgumentException("Itinerary with ID " + itinerary_Id + " not found"));
        Location location = locationRepository.findById(stepDto.getLocation_id())
                .orElseThrow(() -> new IllegalArgumentException("Location with ID " + stepDto.getLocation_id() + " not found"));
        Step newStep = new Step();
        newStep.setTitle(stepDto.getTitle());
        newStep.setDescription(stepDto.getDescription());
        newStep.setNumberDays(stepDto.getNumberDays());
        newStep.setItinerary(itinerary);
        newStep.setLocation(location);
        return stepRepository.save(newStep);
    }

    public Step updateStep(Long stepId, StepDto stepDto, Long itineraryId) {
        Step step = stepRepository.findById(stepId).orElseThrow(() -> new RuntimeException("Step with ID: " + stepId + " not found"));
        Itinerary itinerary = itineraryRepository.findById(itineraryId).orElseThrow(() -> new IllegalArgumentException("Itinerary with ID " + itineraryId + " not found"));
        Location location = locationRepository.findById(stepDto.getLocation_id()).orElseThrow(() -> new IllegalArgumentException("Location with ID " + stepDto.getLocation_id() + " not found"));
        step.setTitle(stepDto.getTitle());
        step.setDescription(stepDto.getDescription());
        step.setNumberDays(stepDto.getNumberDays());
        step.setItinerary(itinerary);
        step.setLocation(location);
        return stepRepository.save(step);
    }

    public void deleteStep(Long stepId) {
        Step step = stepRepository.findById(stepId)
                .orElseThrow(() -> new RuntimeException("Step with ID: " + stepId + " not found"));
        stepRepository.delete(step);
    }


    public String addActivityToStep(Long stepId, Long activityId) {
        Step step = stepRepository.findById(stepId).orElseThrow(() -> new RuntimeException("Step not found"));
        Activity activity = activityRepository.findById(activityId).orElseThrow(() -> new RuntimeException("Activity not found"));
        if (!step.getActivities().contains(activity)) {
            step.getActivities().add(activity);
            stepRepository.save(step);
            return "Activity Added successfully to step.";
        } else {
            return "Activity Already Added to this Step.";
        }
    }


}
