package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.LodgeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Lodge;
import com.ILSI.TouristeProject.Locations.Amenity.Repository.LodgeRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ILodgeService;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class LodgeService implements ILodgeService {

    private final LodgeRepository lodgeRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<Lodge> getAllLodges() {
        return lodgeRepository.findAll();
    }

    @Override
    public Optional<Lodge> findLodgeByName(String name) {
        return lodgeRepository.findByName(name);
    }

    @Override
    public String addLodge(LodgeDto lodgeRequest) throws IOException {
        Optional<Lodge> camping = this.findLodgeByName(lodgeRequest.getName());
        if(camping.isPresent()){
            throw new LocationAlreadyExistException("Lodge with Name "+lodgeRequest.getName()+" Already Exist");
        }
        var newLodge = new Lodge();
        getLodge(lodgeRequest, newLodge);
        return "Success! Lodge Created successfully";
    }

    @Override
    public String UpdateLodge(Long lodge_id, LodgeDto lodgeDto) throws IOException {
        Lodge existingLodge = lodgeRepository.findById(lodge_id)
                .orElseThrow(()-> new LocationNotFoundException("Lodge with ID = "+lodge_id +" Not Found"));

        getLodge(lodgeDto, existingLodge);
        return "Success! Lodge Updated successfully";
    }

    @Override
    public String DeleteLodge(Long lodge_id) {
        Lodge existingLodge = lodgeRepository.findById(lodge_id)
                .orElseThrow(()-> new LocationNotFoundException("Lodge with ID = "+lodge_id +" Not Found"));
        lodgeRepository.delete(existingLodge);
        return "Success! Lodge deleted successfully";
    }

    private void getLodge(LodgeDto lodgeRequest, Lodge newLodge) throws IOException {
       locationImageService.getAmenity(lodgeRequest, newLodge);
        newLodge.setViewPanoramic(lodgeRequest.isViewPanoramic());
        newLodge.setCloseNature(lodgeRequest.isCloseNature());
        lodgeRepository.save(newLodge);
    }
}
