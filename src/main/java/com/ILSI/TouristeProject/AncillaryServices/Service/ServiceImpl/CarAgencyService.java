package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.Repository.CarAgencyRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.ICarAgencyService;
import com.ILSI.TouristeProject.AncillaryServices.dto.CarAgencyDto;
import com.ILSI.TouristeProject.AncillaryServices.model.CarAgency;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CarAgencyService implements ICarAgencyService {

    private final CarAgencyRepository carAgencyRepository;
    private final AncillaryServicesService ancillaryServicesService;

    @Override
    public List<CarAgency> findAllCarAgency() {return carAgencyRepository.findAll();}

    @Override
    public Optional<CarAgency> findCarAgencyByName(String name) {return carAgencyRepository.findCarAgencyByName(name);}

    @Override
    public String addCarAgency(CarAgencyDto carAgencyDto) {
        Optional<CarAgency> existingCarAgency = carAgencyRepository.findCarAgencyByName(carAgencyDto.getName());
        if (existingCarAgency.isPresent()) {
            throw new AncillaryServiceAlreadyExistException("Car Agency with Name "+carAgencyDto.getName()+" Already Exist");
        }
        var newCarAgency = new CarAgency();
        this.getCarAgency(carAgencyDto, newCarAgency);
        return "Success! Car Agency Created successfully";
    }

    @Override
    public String updateCarAgency(Long carAgency_id, CarAgencyDto carAgencyDto) {
        CarAgency existingCarAgency = carAgencyRepository.findById(carAgency_id).orElseThrow(
                ()-> new AncillaryServiceNotFound("Car Agency with ID = "+carAgency_id+" Not Found"));
        this.getCarAgency(carAgencyDto, existingCarAgency);
        return "Success! Car Agency Updated successfully";
    }

    @Override
    public String deleteCarAgency(Long carAgency_id) {
        CarAgency existingCarAgency = carAgencyRepository.findById(carAgency_id).orElseThrow(
                ()-> new AncillaryServiceNotFound("Car Agency with ID = "+carAgency_id+" Not Found"));
        carAgencyRepository.delete(existingCarAgency);
        return "Success! Car Agency Deleted successfully";
    }

    private void getCarAgency(CarAgencyDto carAgencyDto, CarAgency newCarAgency) {
        ancillaryServicesService.getAncillaryService(carAgencyDto, newCarAgency);
        newCarAgency.setVehiclesType(carAgencyDto.getVehiclesType());
        newCarAgency.setWebSiteUrl(carAgencyDto.getWebSiteUrl());
        newCarAgency.setInsuranceIncluded(carAgencyDto.isInsuranceIncluded());
        carAgencyRepository.save(newCarAgency);
    }
}
