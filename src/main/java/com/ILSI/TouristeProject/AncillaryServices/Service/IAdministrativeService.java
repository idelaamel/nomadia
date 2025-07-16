package com.ILSI.TouristeProject.AncillaryServices.Service;

import com.ILSI.TouristeProject.AncillaryServices.dto.AdministrativeDto;
import com.ILSI.TouristeProject.AncillaryServices.model.Administrative;

import java.util.List;
import java.util.Optional;

public interface IAdministrativeService {

    List<Administrative> findAllAdministrative();

    Optional<Administrative> findAdministrativeByName(String name);

    String addAdministrative(AdministrativeDto administrativeDto);

    String updateAdministrative(Long administrative_id, AdministrativeDto administrativeDto);

    String deleteAdministrative(Long administrative_id);
}
