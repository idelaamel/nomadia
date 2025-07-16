package com.ILSI.TouristeProject.Locations.Amenity.Service;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Cafe;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICafeService {

    List<Cafe> getAllCafes();

    Optional<Cafe> findCafeByName(String name);

    String addCafe(CafeDto cafeDto) throws IOException;

    String UpdateCafe(Long cafe_id , CafeDto cafeDto) throws IOException;

    String DeleteCafe(Long cafe_id);

}
