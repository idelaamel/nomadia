package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;


import com.ILSI.TouristeProject.AutreClass.Repository.CityRepository;
import com.ILSI.TouristeProject.AutreClass.Repository.CountryRepository;
import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.AutreClass.model.Country;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.HotelDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Hotel;
import com.ILSI.TouristeProject.Locations.Amenity.Repository.HotelRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Service.IHotelService;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class HotelService implements IHotelService {

    private final HotelRepository hotelRepository;
    private final LocationImageService locationImageService;
    private final CityRepository cityRepository;
    private final CountryRepository countryRepository;

    @Override
    public List<Hotel> getAllHotels() {return hotelRepository.findAll();}


    @Override
    public Optional<Hotel> findByName(String name) {
        return hotelRepository.findByName(name);
    }

    @Override
    public String addHotel(HotelDto hotelDto) throws IOException {
        Optional<Hotel> hotel = this.findByName(hotelDto.getName());
        if(hotel.isPresent()){
            throw new LocationAlreadyExistException("Hotel with Name "+hotelDto.getName()+" Already Exist");
        }
        var newHotel = new Hotel();
        getHotel(hotelDto, newHotel);
        return "Success! Hotel Created successfully";
    }

    @Override
    public String UpdateHotel(Long hotel_id, HotelDto hotelDto) throws IOException {
        Hotel existingHotel = hotelRepository.findById(hotel_id)
                .orElseThrow(()-> new LocationNotFoundException("Hotel with ID = "+hotel_id+" Not Found"));
           getHotel(hotelDto, existingHotel);
         return "Success! Hotel updated successfully";
    }

    @Override
    public String DeleteHotel(Long hotel_id) {
        Hotel existingHotel = hotelRepository.findById(hotel_id)
                .orElseThrow(()-> new LocationNotFoundException("Hotel with ID = "+hotel_id+" Not Found"));
         hotelRepository.delete(existingHotel);
         return "Success! Hotel deleted successfully";
    }

    private void getHotel(HotelDto hotelDto, Hotel newHotel) throws IOException {
        locationImageService.getAmenity(hotelDto, newHotel);
        newHotel.setHasSwimmingPool(hotelDto.isHasSwimmingPool());
        newHotel.setNumberOfRooms(hotelDto.getNumberOfRooms());
        newHotel.setNumberStars(hotelDto.getNumberStars());
        hotelRepository.save(newHotel);
    }


}
