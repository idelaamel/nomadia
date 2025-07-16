package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.Repository.AdministrativeRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.IAdministrativeService;
import com.ILSI.TouristeProject.AncillaryServices.dto.AdministrativeDto;
import com.ILSI.TouristeProject.AncillaryServices.model.Administrative;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdministrativeService implements IAdministrativeService {

    private final AdministrativeRepository administrativeRepository;
    private final AncillaryServicesService ancillaryServicesService;

    @Override
    public List<Administrative> findAllAdministrative() {return administrativeRepository.findAll();}

    @Override
    public Optional<Administrative> findAdministrativeByName(String name) {return administrativeRepository.findAdministrativeByName(name);}

    @Override
    public String addAdministrative(AdministrativeDto administrativeDto) {
        Optional<Administrative> existingAdministrative = administrativeRepository.findAdministrativeByName(administrativeDto.getName());
        if(existingAdministrative.isPresent()){
            throw new AncillaryServiceAlreadyExistException("Administrative Service with Name "+administrativeDto.getName()+" Already Exist");
        }
        this.getAdministrativeService(administrativeDto, new Administrative());
        return "Success! Administrative Service Created successfully";
    }

    @Override
    public String updateAdministrative(Long administrative_id, AdministrativeDto administrativeDto) {
        Administrative existingAdministrative = administrativeRepository.findById(administrative_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Administrative Service with ID = "+administrative_id+" Not Found"));
        this.getAdministrativeService(administrativeDto, existingAdministrative);
        return "Success! Administrative Service Updated successfully";
    }

    @Override
    public String deleteAdministrative(Long administrative_id) {
        Administrative existingAdministrative = administrativeRepository.findById(administrative_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Administrative Service with ID = "+administrative_id+" Not Found"));
        administrativeRepository.delete(existingAdministrative);
        return "Success! Administrative Service deleted successfully";
    }

    private void getAdministrativeService(AdministrativeDto administrativeDto, Administrative newAdministrative) {
        ancillaryServicesService.getAncillaryService(administrativeDto, newAdministrative);
        newAdministrative.setRequiresReservation(administrativeDto.isRequiresReservation());
        newAdministrative.setDocumentRequired(administrativeDto.getDocumentRequired());
        administrativeRepository.save(newAdministrative);
    }
}
