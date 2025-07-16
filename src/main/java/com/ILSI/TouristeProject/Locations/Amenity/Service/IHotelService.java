package com.ILSI.TouristeProject.Locations.Amenity.Service;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.HotelDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Hotel;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IHotelService {

    List<Hotel> getAllHotels();

    Optional<Hotel> findByName(String name);

    String addHotel(HotelDto hotelRequest) throws IOException;

    String UpdateHotel(Long hotel_id , HotelDto hotelDto) throws IOException;

    String DeleteHotel(Long hotel_id);
}
