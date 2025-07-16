package com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl;

import com.ILSI.TouristeProject.Accessibilitys.Repository.TaxiRepository;
import com.ILSI.TouristeProject.Accessibilitys.Service.ITaxiService;
import com.ILSI.TouristeProject.Accessibilitys.dto.TaxiDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaxiService implements ITaxiService {

    private final TaxiRepository taxiRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<Taxi> getAllTaxis() { return taxiRepository.findAll(); }

    @Override
    public Optional<Taxi> findTaxiByNane(String nane) { return taxiRepository.findByName(nane); }

    @Override
    public String addTaxi(TaxiDto taxiDto) throws IOException {
        Optional<Taxi> taxi = taxiRepository.findByName(taxiDto.getName());
        if (taxi.isPresent()) {
            throw new AccessibilityAlreadyExistException("Taxi with Name "+taxiDto.getName()+" Already Exist");
        }
        Taxi newTaxi = new Taxi();
        getTaxi(taxiDto , newTaxi);
        return "Success! Taxi Created Successfully";
    }

    @Override
    public String updateTaxi(Long taxi_id , TaxiDto taxiDto) throws IOException {
        Taxi existingTaxi = taxiRepository.findById(taxi_id)
                .orElseThrow(()-> new AccessibilityNotFoundException("Taxi with Id "+taxi_id+" Not Found"));
        getTaxi(taxiDto, existingTaxi);
        return "Success! Taxi Updated Successfully";
    }

    @Override
    public String deleteTaxi(Long taxi_id) {
        Taxi existiongTaxi = taxiRepository.findById(taxi_id)
                .orElseThrow(()-> new AccessibilityNotFoundException("Taxi with Id"+taxi_id+"Not Found"));
        taxiRepository.delete(existiongTaxi);
        return "Success! Taxi deleted Successfully";
    }

    private void getTaxi(TaxiDto taxiDto, Taxi newTaxi) throws IOException {
        locationImageService.getAccessibility(taxiDto, newTaxi);
        newTaxi.setTaxiNumber(taxiDto.getTaxiNumber());
        newTaxi.setCapacity(taxiDto.getCapacity());
        taxiRepository.save(newTaxi);
    }
}
