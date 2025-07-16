package com.ILSI.TouristeProject.Accessibilitys.Controller;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityRepository;
import com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl.BusService;
import com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl.FlyService;
import com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl.TaxiService;
import com.ILSI.TouristeProject.Accessibilitys.dto.BusDto;
import com.ILSI.TouristeProject.Accessibilitys.dto.FlyDto;
import com.ILSI.TouristeProject.Accessibilitys.dto.TaxiDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@Tag(name = " Explore All Accessibility By Users Her ")
public class PublicAccessibilityController {

    private final BusService busService;
    private final TaxiService taxiService;
    private final FlyService flyService;
    private final AccessibilityRepository accessibilityRepository;

    @GetMapping("/getAccessibilityById/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        return accessibilityRepository.findById(id).map(accessibility -> {
                    if (accessibility instanceof Taxi) {return this.convertTaxiToDTO((Taxi) accessibility);}
                    else if (accessibility instanceof Bus) {return this.convertBusToDTO((Bus) accessibility);}
                    else {return this.convertFlyToDTO((Fly) accessibility);}
                })
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll/Accessibility")
    public List<AccessibilityDto.AccessibilityDtoResponse> getAccessibility() {
        List<Accessibility> listAccessibility = accessibilityRepository.findAll();
        List<AccessibilityDto.AccessibilityDtoResponse> responseList = new ArrayList<>();
        for (Accessibility accessibility : listAccessibility) {
            responseList.add(this.SetAccessibilityFields(accessibility, new AccessibilityDto.AccessibilityDtoResponse()));
        }
        return responseList;
    }

    @GetMapping("/Bus")
    public List<BusDto.BusDtoResponse> getAllBus() throws IOException {
       return busService.getAllBus().stream().map(this::convertBusToDTO).toList();
    }

    @GetMapping("/Taxi")
    public List<TaxiDto.TaxiDtoResponse> getAllTaxi() throws IOException {
        return taxiService.getAllTaxis().stream().map(this::convertTaxiToDTO).toList();
    }

    @GetMapping("/Fly")
    public List<FlyDto.FlyDtoResponse> getAllFly() throws IOException {
        return flyService.getAllFly().stream().map(this::convertFlyToDTO).toList();
    }

    public BusDto.BusDtoResponse convertBusToDTO(Bus bus) {
        BusDto.BusDtoResponse busDtoResponse = new BusDto.BusDtoResponse();
        this.SetAccessibilityFields(bus, busDtoResponse);
        busDtoResponse.setCompany(bus.getCompany());
        busDtoResponse.setRouteName(bus.getRouteName());
        return busDtoResponse;
    }
    public TaxiDto.TaxiDtoResponse convertTaxiToDTO(Taxi taxi) {
        TaxiDto.TaxiDtoResponse taxiDtoResponse = new TaxiDto.TaxiDtoResponse();
        this.SetAccessibilityFields(taxi, taxiDtoResponse);
        taxiDtoResponse.setCapacity(taxi.getCapacity());
        taxiDtoResponse.setTaxiNumber(taxi.getTaxiNumber());
        return taxiDtoResponse;
    }
    public FlyDto.FlyDtoResponse convertFlyToDTO(Fly fly) {
        FlyDto.FlyDtoResponse flyDtoResponse = new FlyDto.FlyDtoResponse();
        this.SetAccessibilityFields(fly, flyDtoResponse);
        flyDtoResponse.setAirline(fly.getAirline());
        flyDtoResponse.setDepartureAirport(fly.getDepartureAirport());
        flyDtoResponse.setArrivalAirport(fly.getArrivalAirport());
        return flyDtoResponse;
    }

    public AccessibilityDto.AccessibilityDtoResponse SetAccessibilityFields(Accessibility source, AccessibilityDto.AccessibilityDtoResponse target) {
        List<String> urls = source.getImages().stream().map(Image::getImageUrl).toList();
        target.setId_Accessibility(source.getId_Accessibility());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setPrice(source.getPrice());
        target.setDepartureTime(source.getDepartureTime());
        target.setArrivalTime(source.getArrivalTime());
        target.setDepartureCity(source.getDepartureCity().getName());
        target.setArrivalCity(source.getArrivalCity().getName());
        target.setDepartureCountry(source.getDepartureCity().getCountry().getName());
        target.setArrivalCountry(source.getArrivalCity().getCountry().getName());
        target.setImages(urls);
        return target;
    }
}
