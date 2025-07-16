package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.Repository.SanitaryRepository;
import com.ILSI.TouristeProject.AncillaryServices.Service.ISanitaryService;
import com.ILSI.TouristeProject.AncillaryServices.dto.SanitaryDto;
import com.ILSI.TouristeProject.AncillaryServices.model.Sanitary;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AncillaryServiceNotFound;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SanitaryService implements ISanitaryService {

    private final SanitaryRepository sanitaryRepository;
    private final AncillaryServicesService ancillaryServicesService;

    @Override
    public List<Sanitary> findAllSanitary() {return sanitaryRepository.findAll();}

    @Override
    public Optional<Sanitary> findSanitaryByName(String name) {return sanitaryRepository.findSanitaryByName(name);}

    @Override
    public String addSanitary(SanitaryDto sanitaryDto) {
        Optional<Sanitary> existingSanitary = sanitaryRepository.findSanitaryByName(sanitaryDto.getName());
        if(existingSanitary.isPresent()){
            throw  new AncillaryServiceAlreadyExistException("Sanitary Service with Name "+sanitaryDto.getName()+" Already Exist");
        }
        this.getSanitaryService(sanitaryDto, new Sanitary());
        return "Success! Sanitary Service Created successfully";
    }

    @Override
    public String updateSanitary(Long sanitary_id, SanitaryDto sanitaryDto) {
        Sanitary existingSanitary = sanitaryRepository.findById(sanitary_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Sanitary Service with ID = "+sanitary_id+" Not Found"));
        this.getSanitaryService(sanitaryDto, existingSanitary);
            return "Success! Sanitary Service Updated successfully";
    }

    @Override
    public String deleteSanitary(Long sanitary_id) {
        Sanitary existingSanitary = sanitaryRepository.findById(sanitary_id)
                .orElseThrow(()-> new AncillaryServiceNotFound("Sanitary Service with ID = "+sanitary_id+" Not Found"));
        sanitaryRepository.delete(existingSanitary);
        return "Success! Sanitary Service Deleted successfully";
    }


    private void getSanitaryService(SanitaryDto sanitaryDto, Sanitary newSanitary){
        ancillaryServicesService.getAncillaryService(sanitaryDto, newSanitary);
        newSanitary.setEmergencySupport(sanitaryDto.isEmergencySupport());
        sanitaryRepository.save(newSanitary);
    }
}
