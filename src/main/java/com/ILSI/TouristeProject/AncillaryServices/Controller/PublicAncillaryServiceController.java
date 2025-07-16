package com.ILSI.TouristeProject.AncillaryServices.Controller;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceDto;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl.*;
import com.ILSI.TouristeProject.AncillaryServices.dto.*;
import com.ILSI.TouristeProject.AncillaryServices.model.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@Tag(name = " Explore All Ancillary Services By Users Her ")
public class PublicAncillaryServiceController {

    private final AdministrativeService administrativeService;
    private final BankService bankService;
    private final CarAgencyService carAgencyService;
    private final SanitaryService sanitaryService;
    private final TourGuidService tourGuidService;
    private final TravelAgencyService travelAgencyService;
    private final AncillaryServiceRepository repository;

    @GetMapping("/getAncillaryServiceById/{id}")
    public ResponseEntity<AncillaryService> getById(@PathVariable Long id) {
        return repository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/getAll/AncillaryService")
    public List<AncillaryServiceDto.AncillaryServiceDtoResponse> getAncillaryService() {
        List<AncillaryService> listService = repository.findAll();
        List<AncillaryServiceDto.AncillaryServiceDtoResponse> responseList = new ArrayList<>();
        for (AncillaryService service: listService) {
            responseList.add(this.SetAncillaryServiceFields(service, new AncillaryServiceDto.AncillaryServiceDtoResponse()));
        }
        return responseList;
    }

    @GetMapping("/Service/Administrative")
    public List<AdministrativeDto.AdministrativeDtoResponse> getAllAdministrative() {return administrativeService.findAllAdministrative().stream().map(this::convertAdServiceToDto).toList();}

    @GetMapping("/Service/Banks")
    public List<BankDto.BankDtoResponse> getAllABank() {return bankService.findAllBanks().stream().map(this::convertBankToDto).toList();}

    @GetMapping("/Service/CarAgency")
    public List<CarAgencyDto.CarAgencyDtoResponse> getAllCarAgency(){return carAgencyService.findAllCarAgency().stream().map(this::convertCarAgencyToDto).toList();}

    @GetMapping("/Service/Sanitary")
    public List<SanitaryDto.SanitaryDtoResponse> getAllSanitaryServices(){ return sanitaryService.findAllSanitary().stream().map(this::convertSanitaryToDto).toList();}

    @GetMapping("/Service/TourGuide")
    public List<TourGuideDto.TourGuideDtoResponse> getAllTourGuides(){ return tourGuidService.findAllTourGuides().stream().map(this::convertTourGuideToDto).toList();}

    @GetMapping("/Service/TravelAgency")
    public List<TravelAgencyDto.TravelAgencyDtoResponse> getAllTravelAgency(){return travelAgencyService.findAllTravelAgency().stream().map(this::convertTravelAgencyToDto).toList();}

    public AdministrativeDto.AdministrativeDtoResponse convertAdServiceToDto(Administrative administrative) {
        AdministrativeDto.AdministrativeDtoResponse response = new AdministrativeDto.AdministrativeDtoResponse();
        this.SetAncillaryServiceFields(administrative, response);
        response.setRequiresReservation(administrative.isRequiresReservation());
        response.setDocumentRequired(administrative.getDocumentRequired());
        return response;
    }

    public BankDto.BankDtoResponse convertBankToDto(Bank bank) {
        BankDto.BankDtoResponse response = new BankDto.BankDtoResponse();
        this.SetAncillaryServiceFields(bank, response);
        response.setCurrencyExchange(bank.isCurrencyExchange());
        response.setBankName(bank.getBankName());
        return response;
    }
    public CarAgencyDto.CarAgencyDtoResponse convertCarAgencyToDto(CarAgency carAgency){
        CarAgencyDto.CarAgencyDtoResponse response = new CarAgencyDto.CarAgencyDtoResponse();
        this.SetAncillaryServiceFields(carAgency, response);
        response.setInsuranceIncluded(carAgency.isInsuranceIncluded());
        response.setWebSiteUrl(carAgency.getWebSiteUrl());
        response.setVehiclesType(carAgency.getVehiclesType());
        return response;
    }
    public SanitaryDto.SanitaryDtoResponse convertSanitaryToDto(Sanitary sanitary){
        SanitaryDto.SanitaryDtoResponse response = new SanitaryDto.SanitaryDtoResponse();
        this.SetAncillaryServiceFields(sanitary, response);
        response.setEmergencySupport(sanitary.isEmergencySupport());
        return response;
    }
    public TourGuideDto.TourGuideDtoResponse convertTourGuideToDto(TourGuide guide){
        TourGuideDto.TourGuideDtoResponse response = new TourGuideDto.TourGuideDtoResponse();
        this.SetAncillaryServiceFields(guide, response);
        response.setGroupSizeLimit(guide.getGroupSizeLimit());
        response.setLanguageSupported(guide.getLanguageSupported());
        return response;
    }
    public TravelAgencyDto.TravelAgencyDtoResponse convertTravelAgencyToDto(TravelAgency travelAgency){
        TravelAgencyDto.TravelAgencyDtoResponse response = new TravelAgencyDto.TravelAgencyDtoResponse();
        this.SetAncillaryServiceFields(travelAgency, response);
        response.setWebSiteUrl(travelAgency.getWebSiteUrl());
        return response;
    }

   public AncillaryServiceDto.AncillaryServiceDtoResponse SetAncillaryServiceFields(AncillaryService service, AncillaryServiceDto.AncillaryServiceDtoResponse response){
        response.setId_AncillaryService(service.getId_AncillaryService());
        response.setName(service.getName());
        response.setDescription(service.getDescription());
        response.setPhoneNumber(service.getPhoneNumber());
        response.setEmail(service.getEmail());
        response.setLatitude(service.getLatitude());
        response.setLongitude(service.getLongitude());
        response.setOperationHours(service.getOperationHours());
        return response;
   }
}
