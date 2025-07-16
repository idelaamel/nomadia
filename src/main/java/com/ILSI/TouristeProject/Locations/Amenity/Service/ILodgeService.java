package com.ILSI.TouristeProject.Locations.Amenity.Service;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.LodgeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Lodge;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ILodgeService {


    List<Lodge> getAllLodges();

    Optional<Lodge> findLodgeByName(String name);

    String addLodge(LodgeDto lodgeRequest) throws IOException;

    String UpdateLodge(Long lodge_id , LodgeDto lodgeDto) throws IOException;

    String DeleteLodge(Long lodge_id);
}
