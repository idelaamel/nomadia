package com.ILSI.TouristeProject.UserManagement.Controller;

import com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl.BusService;
import com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl.FlyService;
import com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl.TaxiService;
import com.ILSI.TouristeProject.Accessibilitys.dto.BusDto;
import com.ILSI.TouristeProject.Accessibilitys.dto.FlyDto;
import com.ILSI.TouristeProject.Accessibilitys.dto.TaxiDto;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityNotFoundException;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name="Accessibility Management By Admin", description = "Create, Update, Delete Accessibility")
public class AdminAccessibilityController {

    private final TaxiService taxiService;
    private final BusService busService;
    private final FlyService flyService;

    /* ******************************* Create ************************************************ */


    @PostMapping(value = "/addBus", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addBus(@Valid @ModelAttribute BusDto busDto) throws IOException {
        try { return busService.addBus(busDto);}
        catch(AccessibilityAlreadyExistException e){ return e.getMessage();}
    }


    @PostMapping(value = "/addTaxi", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addTaxi(@Valid @ModelAttribute TaxiDto taxiDto) throws IOException {
        try{ return taxiService.addTaxi(taxiDto);}
        catch (AccessibilityAlreadyExistException e){ return e.getMessage();}
    }

    @PostMapping(value = "/addFly", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addFly(@Valid @ModelAttribute FlyDto flyDto) throws IOException {
        try{return flyService.addFly(flyDto);}
        catch (AccessibilityAlreadyExistException e){ return e.getMessage();}
    }

/* ******************************* Update ************************************************ */
    @PutMapping(value = "/updateBus/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateBus(@PathVariable Long id, @ModelAttribute BusDto busDto) throws IOException  {
        try{ return busService.updateBus(id, busDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @PutMapping(value = "/updateTaxi/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateTaxi(@PathVariable Long id, @ModelAttribute TaxiDto taxiDto) throws IOException {
        try{ return taxiService.updateTaxi(id, taxiDto);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }
    @PutMapping(value = "/updateFly/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addFly(@PathVariable Long id, @ModelAttribute FlyDto flyDto) throws IOException {
        try{return  flyService.updateFly(id, flyDto);}
        catch (AccessibilityAlreadyExistException e){ return e.getMessage();}
    }

/* ******************************* Delete ************************************************ */
    @DeleteMapping("/deleteBus/{id}")
    public String deleteBus(@PathVariable Long id) {
        try{ return busService.deleteBus(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/deleteTaxi/{id}")
    public String deleteTaxi(@PathVariable Long id) {
        try{ return taxiService.deleteTaxi(id);}
        catch(AccessibilityNotFoundException e){ return e.getMessage();}
    }

    @DeleteMapping("/deleteFly/{id}")
    public String deleteFly(@PathVariable Long id) {
        try {return flyService.deleteFly(id);}
        catch (AccessibilityNotFoundException e){ return e.getMessage();}
    }

}
