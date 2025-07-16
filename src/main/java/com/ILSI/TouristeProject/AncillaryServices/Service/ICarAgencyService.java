package com.ILSI.TouristeProject.AncillaryServices.Service;

import com.ILSI.TouristeProject.AncillaryServices.dto.CarAgencyDto;
import com.ILSI.TouristeProject.AncillaryServices.model.CarAgency;

import java.util.List;
import java.util.Optional;

public interface ICarAgencyService {

    List<CarAgency> findAllCarAgency();

    Optional<CarAgency> findCarAgencyByName(String name);

    String addCarAgency(CarAgencyDto carAgencyDto);

    String updateCarAgency(Long carAgency_id, CarAgencyDto carAgencyDto);

    String deleteCarAgency(Long carAgency_id);
}
