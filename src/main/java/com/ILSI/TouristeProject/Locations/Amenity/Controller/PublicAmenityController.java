package com.ILSI.TouristeProject.Locations.Amenity.Controller;

import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import com.ILSI.TouristeProject.Locations.Amenity.AmenityRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.*;
import com.ILSI.TouristeProject.Locations.Amenity.Model.*;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@Tag(name = " Explore All Amenity By Users Her ")
public class PublicAmenityController {

    private final HotelService hotelService;
    private final CafeService cafeService;
    private final CampingService campingService;
    private final LodgeService lodgeService;
    private final RestaurantService restaurantService;
    private final GuestHouseService guestHouseService;
    private final AmenityRepository amenityRepository;


    @GetMapping("/getAll/Amenities")
    public List<AmenityDto.AmenityDtoResponse> getAmenities() {
        List<Amenity> listAmenity = amenityRepository.findAll();
        List<AmenityDto.AmenityDtoResponse> responseList = new ArrayList<>();
        for (Amenity amenity: listAmenity) {
            responseList.add(this.SetAmenityFields(amenity, new AmenityDto.AmenityDtoResponse()));
        }
        return responseList;
    }

    @GetMapping("/Hotels")
    public List<HotelDto.HotelDtoResponse> getHotels() {return hotelService.getAllHotels().stream().map(this::convertHotelToDTO).toList();}

    @GetMapping("/Cafes")
    public List<CafeDto.CafeDtoResponse> getCafes() {return cafeService.getAllCafes().stream().map(this::convertCafeToDTO).toList();}

    @GetMapping("/Camping")
    public List<CampingDto.CampingDtoResponse> getCamping() {return campingService.getAllCamping().stream().map(this::convertCampingToDTO).toList();}

    @GetMapping("/Restaurants")
    public List<RestaurantDto.RestaurantDtoResponse> getRestaurants() {return restaurantService.getAllRestaurants().stream().map(this::convertRestaurantToDTO).toList();}

    @GetMapping("/Lodges")
    public List<LodgeDto.LodgeDtoResponse> getLodges() {return lodgeService.getAllLodges().stream().map(this::convertLodgeToDTO).toList();}

    @GetMapping("/GuestHouses")
    public List<GuestHouseDto.GuestHouseDtoResponse> getGuestHouses() {return guestHouseService.getAllGuestHouse().stream().map(this::convertGuestHouseToDTO).toList();}


    public HotelDto.HotelDtoResponse convertHotelToDTO(Hotel hotel) {
        HotelDto.HotelDtoResponse hotelDto = new HotelDto.HotelDtoResponse();
        this.SetAmenityFields(hotel, hotelDto);
        hotelDto.setNumberStars(hotel.getNumberStars());
        hotelDto.setNumberOfRooms(hotel.getNumberOfRooms());
        hotelDto.setHasSwimmingPool(hotel.isHasSwimmingPool());
        return hotelDto;
    }
    public LodgeDto.LodgeDtoResponse convertLodgeToDTO(Lodge lodge) {
        LodgeDto.LodgeDtoResponse lodgeDto = new LodgeDto.LodgeDtoResponse();
        this.SetAmenityFields(lodge, lodgeDto);
        lodgeDto.setViewPanoramic(lodge.isViewPanoramic());
        lodgeDto.setCloseNature(lodge.isCloseNature());
        return lodgeDto;
    }

    public CafeDto.CafeDtoResponse convertCafeToDTO(Cafe cafe) {
        CafeDto.CafeDtoResponse cafeDtoResponse = new CafeDto.CafeDtoResponse();
        this.SetAmenityFields(cafe, cafeDtoResponse);
        cafeDtoResponse.setMenu(cafe.getMenu());
        cafeDtoResponse.setAvailable(cafe.isAvailable());
        return cafeDtoResponse;
    }
    public CampingDto.CampingDtoResponse convertCampingToDTO(Camping camping) {
        CampingDto.CampingDtoResponse campingDtoResponse = new CampingDto.CampingDtoResponse();
        this.SetAmenityFields(camping, campingDtoResponse);
        campingDtoResponse.setCapacity(camping.getCapacity());
        campingDtoResponse.setElectricityAvailability(camping.isElectricityAvailability());
        campingDtoResponse.setHasWaterSupply(camping.isHasWaterSupply());
        campingDtoResponse.setSanitaryAvailability(camping.isSanitaryAvailability());
        return campingDtoResponse;
    }

    public GuestHouseDto.GuestHouseDtoResponse convertGuestHouseToDTO(GuestHouse guestHouse) {
        GuestHouseDto.GuestHouseDtoResponse guestHouseDtoResponse= new GuestHouseDto.GuestHouseDtoResponse();
        this.SetAmenityFields(guestHouse, guestHouseDtoResponse);
        guestHouseDtoResponse.setBreakfastIncluded(guestHouse.isBreakfastIncluded());
        guestHouseDtoResponse.setNumberRooms(guestHouse.getNumberRooms());
        return guestHouseDtoResponse;
    }

    public RestaurantDto.RestaurantDtoResponse convertRestaurantToDTO(Restaurant restaurant) {
        RestaurantDto.RestaurantDtoResponse restaurantDtoResponse = new RestaurantDto.RestaurantDtoResponse();
        this.SetAmenityFields(restaurant, restaurantDtoResponse);
        restaurantDtoResponse.setMenu(restaurant.getMenu());
        restaurantDtoResponse.setTypeCuisine(restaurant.getTypeCuisine());
        return restaurantDtoResponse;
    }

    public AmenityDto.AmenityDtoResponse SetAmenityFields(Amenity source, AmenityDto.AmenityDtoResponse target) {
        List<String> urls = source.getImages().stream().map(Image::getImageUrl).toList();
        target.setId_Location(source.getId_Location());
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setImageUrls(urls);
        target.setAvailable(source.isAvailable());
        target.setOpeningHours(source.getOpeningHours());
        target.setPrice(source.getPrice());
        target.setCityName(source.getCity().getName());
        target.setCountryName(source.getCity().getCountry().getName());
        target.setLatitude(source.getLatitude());
        target.setLongitude(source.getLongitude());
        return target;
    }
}
