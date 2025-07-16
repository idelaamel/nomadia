package com.ILSI.TouristeProject.AutreClass.Controller;

import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Activitis.ActivityDto;
import com.ILSI.TouristeProject.Activitis.Controller.PublicActivityController;
import com.ILSI.TouristeProject.AutreClass.Service.ItineraryService;
import com.ILSI.TouristeProject.AutreClass.Service.StepService;
import com.ILSI.TouristeProject.AutreClass.Service.VisitService;
import com.ILSI.TouristeProject.AutreClass.dto.ItineraryDto;
import com.ILSI.TouristeProject.AutreClass.dto.StepDto;
import com.ILSI.TouristeProject.AutreClass.dto.VisitDto;
import com.ILSI.TouristeProject.AutreClass.model.Itinerary;
import com.ILSI.TouristeProject.AutreClass.model.Step;
import com.ILSI.TouristeProject.AutreClass.model.Visit;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name="Visit Management By Users", description = "Create, Update and Delete Your Visits")
public class VisitController {

    private final VisitService visitService;
    private final ItineraryService itineraryService;
    private final StepService stepService;
    private final PublicActivityController controller;

//****************** Create a new Visit ********************************************************************************
    @PostMapping("/Create/Visit")
    public ResponseEntity<VisitDto.VisitDtoResponse> createVisit(@Valid @RequestBody VisitDto visitRequestDTO) {
        Visit createdVisit = visitService.createVisit(visitRequestDTO);
        return new ResponseEntity<>(this.convertVisitToDto(createdVisit), HttpStatus.CREATED);
    }
//****************** Update Visit By id ********************************************************************************
    @PutMapping("/Update/Visit/{id}")
    public ResponseEntity<?> updateVisit(@PathVariable Long id, @Valid @RequestBody VisitDto visitRequestDTO) {
        try {Visit updatedVisit = visitService.updateVisit(id, visitRequestDTO);
            return ResponseEntity.ok(this.convertVisitToDto(updatedVisit));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }}
//****************** Delete Visit by id ********************************************************************************
    @DeleteMapping("/Delete/Visit/{id}")
    public ResponseEntity<String> deleteVisit(@PathVariable Long id) {
        try {visitService.deleteVisit(id);
            return ResponseEntity.ok("Visit deleted successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }}
//****************** Search All Visit ********************************************************************************
    @GetMapping("/Show/Visit")
    public ResponseEntity<List<VisitDto.VisitDtoResponse>> showAllVisit() {
        List<Visit> listVisit = visitService.findAllVisit();
        List<VisitDto.VisitDtoResponse> visitDtoResponses = listVisit.stream().map(this::convertVisitToDto).collect(Collectors.toList());
        return new ResponseEntity<>(visitDtoResponses, HttpStatus.OK);
    }
//****************** Search Visit By Title ***************************************************************************
    @GetMapping("/Show/Visit/{title}")
    public ResponseEntity<VisitDto.VisitDtoResponse> showVisit(@PathVariable String title) {
      Visit visit = visitService.findVisitByTitle(title).orElseThrow(()->new RuntimeException("Visit not found"));
      return new ResponseEntity<>(this.convertVisitToDto(visit), HttpStatus.OK);
    }
//****************** Create a new Itinerary **************************************************************************
    @PostMapping("/Create/Itinerary/{id_visit}")
    public ResponseEntity<ItineraryDto.ItineraryDtoResponse> createItinerary(@RequestBody ItineraryDto itineraryDTO, @PathVariable Long id_visit) {
        Itinerary createdItinerary = itineraryService.CreateNewItinerary(itineraryDTO, id_visit);
        return new ResponseEntity<>(getItineraryDto(createdItinerary),HttpStatus.CREATED);
    }
//****************** Update Itinerary By id **************************************************************************
    @PutMapping("/Update/Itinerary/{id}")
    public ResponseEntity<?> updateItinerary(@PathVariable("id") Long itineraryId, @RequestParam("visitId") Long visitId,@Valid @RequestBody ItineraryDto itineraryDto) {
        try {Itinerary updatedItinerary = itineraryService.updateItinerary(itineraryId, itineraryDto, visitId);
            return ResponseEntity.ok(getItineraryDto(updatedItinerary));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }}
//****************** Delete Itinerary By id **************************************************************************
    @DeleteMapping("/Delete/Itinerary/{id}")
    public ResponseEntity<String> deleteItinerary(@PathVariable("id") Long itineraryId) {
        try {itineraryService.deleteItinerary(itineraryId);
            return ResponseEntity.ok("Itinerary Deleted Successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }}
//****************** search Itinerary By name ************************************************************************
    @GetMapping("/Show/Itinerary/{name}")
    public ResponseEntity<ItineraryDto.ItineraryDtoResponse> showItinerary(@PathVariable String name) {
        Itinerary itinerary = itineraryService.findItineraryByName(name).orElseThrow(()->new RuntimeException("Itinerary Not Found"));
        return new ResponseEntity<>(getItineraryDto(itinerary), HttpStatus.OK);
    }
//****************** Create a new Step *******************************************************************************
    @PostMapping("/Create/Step/{itinerary_id}")
    public ResponseEntity<StepDto.StepDtoResponse> createStep(@Valid @RequestBody StepDto stepDto,@PathVariable Long itinerary_id){
        Step step = stepService.CreateNewStep(stepDto, itinerary_id);
        return new ResponseEntity<>(getStepDto(step), HttpStatus.CREATED);
    }
//****************** Update Step By id *******************************************************************************
    @PutMapping("/Update/Step/{id}")
    public ResponseEntity<?> updateStep(@PathVariable("id") Long stepId, @RequestParam("itineraryId") Long itineraryId, @Valid @RequestBody StepDto stepDto) {
        try {Step updatedStep = stepService.updateStep(stepId, stepDto, itineraryId);
            return ResponseEntity.ok(getStepDto(updatedStep));
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }}
//****************** Delete Step By id *******************************************************************************
    @DeleteMapping("/Delete/Step/{id}")
    public ResponseEntity<String> deleteStep(@PathVariable("id") Long stepId) {
        try {stepService.deleteStep(stepId);
            return ResponseEntity.ok("Step Deleted Successfully");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + ex.getMessage());
        }}
//****************** Search Step By Title ****************************************************************************
    @GetMapping("/Show/Step/{title}")
    public ResponseEntity<StepDto.StepDtoResponse> showStep(@PathVariable String title) {
        Step step = stepService.findStepByTitle(title).orElseThrow(()->new RuntimeException("Step Not Found"));
        return new ResponseEntity<>(getStepDto(step), HttpStatus.OK);
    }
//****************** add an Activity to a Step ***********************************************************************
    @PostMapping("/{stepId}/activities/{activityId}")
    public ResponseEntity<String> addActivityToStep(@PathVariable Long stepId, @PathVariable Long activityId) {
        String response = stepService.addActivityToStep(stepId, activityId);
        return ResponseEntity.ok(response);
    }



    private StepDto.StepDtoResponse getStepDto(Step step) {
        StepDto.StepDtoResponse response = new StepDto.StepDtoResponse();
        response.setId_Step(step.getId_step());
        response.setTitle(step.getTitle());
        response.setDescription(step.getDescription());
        response.setNumberDays(step.getNumberDays());
        response.setItineraryName(step.getItinerary().getName());
        response.setLocationName(step.getLocation().getName());
        List<ActivityDto.ActivityDtoResponse> activities = new ArrayList<>();
        for(Activity activity : step.getActivities()){
            ActivityDto.ActivityDtoResponse activityDtoResponse = new ActivityDto.ActivityDtoResponse();
            controller.SetActivityFields(activity,activityDtoResponse);
            activities.add(activityDtoResponse);
        }
        response.setActivities(activities);
        return response;
    }

    private VisitDto.VisitDtoResponse convertVisitToDto(Visit visit){
        VisitDto.VisitDtoResponse response =new VisitDto.VisitDtoResponse();
        response.setId_Visit(visit.getId_Visit());
        response.setTitle(visit.getTitle());
        response.setDescription(visit.getDescription());
        response.setStartDate(visit.getStartDate());
        response.setEndDate(visit.getEndDate());
        response.setNumberDays(visit.getNumberDays());
        response.setUsername(visit.getUser().getNameUser());
        List<ItineraryDto.ItineraryDtoResponse> descIt = new ArrayList<>();
        for(Itinerary itinerary: visit.getItineraries()){
            ItineraryDto.ItineraryDtoResponse itineraryDto = getItineraryDto(itinerary);
            descIt.add(itineraryDto);
        }
        response.setItinerary(descIt);
        return response;
    }

    private ItineraryDto.ItineraryDtoResponse getItineraryDto(Itinerary itinerary) {
        ItineraryDto.ItineraryDtoResponse itineraryDto = new ItineraryDto.ItineraryDtoResponse();
        itineraryDto.setId_Itinerary(itinerary.getId_itinerary());
        itineraryDto.setName(itinerary.getName());
        itineraryDto.setDescription(itinerary.getDescription());
        itineraryDto.setStartDate(itinerary.getStartDate());
        itineraryDto.setEndDate(itinerary.getEndDate());
        itineraryDto.setDepartureCityName(itinerary.getDepartureCity().getName());
        itineraryDto.setArrivalCityName(itinerary.getArrivalCity().getName());
        itineraryDto.setVisitTitle(itinerary.getVisit().getTitle());
        List<StepDto.StepDtoResponse> steps = new ArrayList<>();
        for(Step step: itinerary.getSteps()){
            StepDto.StepDtoResponse stepDtoResponse = this.getStepDto(step);
            steps.add(stepDtoResponse);
        }
        itineraryDto.setSteps(steps);
        return itineraryDto;
    }

}
