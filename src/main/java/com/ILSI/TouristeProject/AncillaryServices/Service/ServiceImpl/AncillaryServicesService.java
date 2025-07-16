package com.ILSI.TouristeProject.AncillaryServices.Service.ServiceImpl;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceDto;
import org.springframework.stereotype.Service;

@Service
public class AncillaryServicesService {





    public void getAncillaryService(AncillaryServiceDto serviceDto, AncillaryService newAncillaryService) {
        newAncillaryService.setName(serviceDto.getName());
        newAncillaryService.setDescription(serviceDto.getDescription());
        newAncillaryService.setLatitude(serviceDto.getLatitude());
        newAncillaryService.setLongitude(serviceDto.getLongitude());
        newAncillaryService.setEmail(serviceDto.getEmail());
        newAncillaryService.setOperationHours(serviceDto.getOperationHours());
        newAncillaryService.setPhoneNumber(serviceDto.getPhoneNumber());
    }
}
