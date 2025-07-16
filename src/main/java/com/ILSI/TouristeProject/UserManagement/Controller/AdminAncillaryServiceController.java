package com.ILSI.TouristeProject.UserManagement.Controller;


import com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl.*;
import com.ILSI.TouristeProject.AncillaryServices.dto.*;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityNotFoundException;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name="Ancillary Service Management By Admin", description = "Create, Update, Delete an Ancillary Service ")
public class AdminAncillaryServiceController {
    private final AdministrativeService administrativeService;
    private final BankService bankService;
    private final CarAgencyService carAgencyService;
    private final SanitaryService sanitaryService;
    private final TourGuidService tourGuidService;
    private final TravelAgencyService travelAgencyService;


/* ******************************* Create ************************************************ */
    @PostMapping(value = "/add/Service/Administrative")
    public String addAdministrativeService(@Valid @RequestBody AdministrativeDto administrativeDto){
        try { return administrativeService.addAdministrative(administrativeDto);}
        catch(AncillaryServiceAlreadyExistException e){ return e.getMessage();}
    }
    @PostMapping(value = "/add/Service/Bank")
    public String addBank(@Valid @RequestBody BankDto bankDto){
        try { return bankService.addBank(bankDto);}
        catch(AncillaryServiceAlreadyExistException e){ return e.getMessage();}
    }
    @PostMapping(value = "/add/Service/CarAgency")
    public String addCarAgency(@Valid @RequestBody CarAgencyDto carAgencyDto){
        try { return carAgencyService.addCarAgency(carAgencyDto);}
        catch(AncillaryServiceAlreadyExistException e){ return e.getMessage();}
    }
    @PostMapping(value = "/add/Service/SanitaryService")
    public String addSanitaryService(@Valid @RequestBody SanitaryDto sanitaryDto){
        try { return sanitaryService.addSanitary(sanitaryDto);}
        catch(AncillaryServiceAlreadyExistException e){ return e.getMessage();}
    }
    @PostMapping(value = "/add/Service/TourGuide")
    public String addTourGuide(@Valid @RequestBody TourGuideDto tourGuideDto){
        try { return tourGuidService.addTourGuide(tourGuideDto);
        } catch(AncillaryServiceAlreadyExistException e){ return e.getMessage();}
    }

    @PostMapping(value = "/add/Service/TravelAgency")
    public String addTravelAgency(@Valid @RequestBody TravelAgencyDto travelAgencyDto){
        try { return travelAgencyService.addTravelAgency(travelAgencyDto);}
        catch(AncillaryServiceAlreadyExistException e){ return e.getMessage();}
    }

/* ******************************* Update ************************************************ */
    @PutMapping(value = "/update/Service/AdministrativeService/{id}")
    public String updateAdministrativeService(@PathVariable Long id, @RequestBody AdministrativeDto administrativeDto){
        try{ System.out.println(administrativeDto);
            return administrativeService.updateAdministrative(id, administrativeDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @PutMapping(value = "/update/Service/Bank/{id}")
    public String updateBank(@PathVariable Long id, @RequestBody BankDto bankDto){
        try{ return bankService.updateBank(id, bankDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @PutMapping(value = "/update/Service/CarAgency/{id}")
    public String updateCarAgency(@PathVariable Long id, @RequestBody CarAgencyDto carAgencyDto){
        try{ return carAgencyService.updateCarAgency(id, carAgencyDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @PutMapping(value = "/update/Service/SanitaryService/{id}")
    public String updateSanitaryService(@PathVariable Long id, @RequestBody SanitaryDto sanitaryDto){
        try{ return sanitaryService.updateSanitary(id, sanitaryDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @PutMapping(value = "/update/Service/TourGuide/{id}")
    public String updateTourGuide(@PathVariable Long id, @RequestBody TourGuideDto tourGuideDto){
        try{ return tourGuidService.updateTourGuide(id, tourGuideDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @PutMapping(value = "/update/Service/TravelAgency/{id}")
    public String updateTravelAgency(@PathVariable Long id, @RequestBody TravelAgencyDto travelAgencyDto){
        try{ return travelAgencyService.updateTravelAgency(id, travelAgencyDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

/* ******************************* Delete ************************************************ */
    @DeleteMapping("/delete/Service/AdministrativeService/{id}")
    public String deleteAdministrativeService(@PathVariable Long id) {
        try{ return administrativeService.deleteAdministrative(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/delete/Service/Bank/{id}")
    public String deleteBank(@PathVariable Long id) {
        try{ return bankService.deleteBank(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/delete/Service/CarAgency/{id}")
    public String deleteCarAgency(@PathVariable Long id) {
        try{ return carAgencyService.deleteCarAgency(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/delete/Service/Sanitary/{id}")
    public String deleteSanitary(@PathVariable Long id) {
        try{ return sanitaryService.deleteSanitary(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/delete/Service/TourGuide/{id}")
    public String deleteTourGuide(@PathVariable Long id) {
        try{ return tourGuidService.deleteTourGuide(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/delete/Service/TravelAgency/{id}")
    public String deleteTravelAgency(@PathVariable Long id) {
        try{ return travelAgencyService.deleteTravelAgency(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }
}
