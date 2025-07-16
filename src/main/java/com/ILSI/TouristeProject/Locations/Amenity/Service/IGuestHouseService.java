package com.ILSI.TouristeProject.Locations.Amenity.Service;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.CafeDto;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.GuestHouseDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.GuestHouse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IGuestHouseService {


    List<GuestHouse> getAllGuestHouse();

    Optional<GuestHouse> findGuestHouseByName(String name);

    String addGuestHouse(GuestHouseDto guestHouseRequest) throws IOException;

    String UpdateGuestHouse(Long guestHouse_id , GuestHouseDto guestHouseDto) throws IOException;

    String DeleteGuestHouse(Long guestHouse_id);
}
