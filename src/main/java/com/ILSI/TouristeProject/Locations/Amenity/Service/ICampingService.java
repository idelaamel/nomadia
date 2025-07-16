package com.ILSI.TouristeProject.Locations.Amenity.Service;


import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.CampingDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Camping;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICampingService {

        List<Camping> getAllCamping();

        Optional<Camping> findCampingByName(String name);

        String addCamping(CampingDto campingDto) throws IOException;

        String UpdateCamping(Long camping_id , CampingDto campingDto) throws IOException;

        String DeleteCamping(Long camping_id);
}
