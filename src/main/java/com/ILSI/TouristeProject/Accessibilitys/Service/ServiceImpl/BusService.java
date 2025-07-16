package com.ILSI.TouristeProject.Accessibilitys.Service.ServiceImpl;

import com.ILSI.TouristeProject.Accessibilitys.Repository.BusRepository;
import com.ILSI.TouristeProject.Accessibilitys.Service.IBusService;
import com.ILSI.TouristeProject.Accessibilitys.dto.BusDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;

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
public class BusService implements IBusService {

    private final BusRepository busRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<Bus> getAllBus() { return busRepository.findAll(); }

    @Override
    public Optional<Bus> findBusByName(String name) {return busRepository.findByName(name);}

    @Override
    public String addBus(BusDto busDto) throws IOException {
        Optional<Bus> bus = busRepository.findByName(busDto.getName());
        if (bus.isPresent()) {
          throw new AccessibilityAlreadyExistException("Bus with Name "+busDto.getName()+" Already Exist");
        }
        getBus(busDto, new Bus());
        return "Success! Bus Created Successfully";
    }

    @Override
    public String updateBus(Long bus_id, BusDto busDto) throws IOException {
          Bus bus = busRepository.findById(bus_id).orElseThrow(()-> new AccessibilityNotFoundException("Bus with ID = "+ bus_id +" Not Found"));
          getBus(busDto, bus);
          return "Success! Bus Updated Successfully";
    }

    @Override
    public String deleteBus(Long bus_id) {
        Bus existingBus = busRepository.findById(bus_id)
                .orElseThrow(()-> new AccessibilityNotFoundException("Bus with ID = "+ bus_id +" Not Found"));
                busRepository.delete(existingBus);
        return "Success! Bus deleted Successfully";
    }

    private void getBus(BusDto busDto, Bus newBus) throws IOException {
        locationImageService.getAccessibility(busDto, newBus);
        newBus.setCompany(busDto.getCompany());
        newBus.setRouteName(busDto.getRouteName());
        busRepository.save(newBus);
    }
}
