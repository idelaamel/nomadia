package com.ILSI.TouristeProject.UserManagement.Controller;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.*;
import com.ILSI.TouristeProject.Locations.Amenity.Model.*;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.*;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name="Amenities Management By Admin", description = "Create, Update and Delete Amenities")
public class AdminAmenityController {

    private final CafeService cafeService;
    private final CampingService campingService;
    private final HotelService hotelService;
    private final LodgeService lodgeService;
    private final RestaurantService restaurantService;
    private final GuestHouseService guestHouseService;


/******************************************  Create  ******************************************************/
    @PostMapping(value = "/addCafe", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addCafe(@Valid @ModelAttribute CafeDto cafeDto) throws  IOException{
        try { return cafeService.addCafe(cafeDto); }
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addCamping", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addCamping(@Valid @ModelAttribute CampingDto campingDto) throws  IOException{
        try {return campingService.addCamping(campingDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addHotel", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addHotel(@Valid @ModelAttribute HotelDto hotelDto) throws IOException {
        try { return hotelService.addHotel(hotelDto);
        }catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addLodge", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addLodge(@Valid @ModelAttribute LodgeDto lodgeDto) throws  IOException{
        try { return lodgeService.addLodge(lodgeDto);
        }catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addGuestHouse", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addGuestHouse(@Valid @ModelAttribute GuestHouseDto guestHouseDto) throws  IOException{
        try {return guestHouseService.addGuestHouse(guestHouseDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }

    @PostMapping(value = "/addRestaurant", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String addRestaurant(@Valid @ModelAttribute RestaurantDto restaurantDto) throws  IOException{
        try {return restaurantService.addRestaurant(restaurantDto);}
        catch (LocationAlreadyExistException e){return e.getMessage();}
    }

/******************************************  Update  ******************************************************/
    @PutMapping(value = "/updateCafe/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateCafe(@PathVariable Long id, @ModelAttribute CafeDto cafeDto) throws IOException{
        try { return cafeService.UpdateCafe(id, cafeDto);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @PutMapping(value = "/updateHotel/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateHotel(@PathVariable Long id, @ModelAttribute HotelDto hotelDto) throws IOException{
        try { return hotelService.UpdateHotel(id,hotelDto);}
        catch (LocationNotFoundException e){return e.getMessage();}
    }

    @PutMapping(value = "/updateCamping/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateCamping(@PathVariable Long id, @ModelAttribute CampingDto campingDto) throws  IOException{
        try{ return campingService.UpdateCamping(id,campingDto); }
        catch (LocationNotFoundException e){return e.getMessage();}
    }

    @PutMapping(value = "/updateRestaurant/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateRestaurant(@PathVariable Long id, @ModelAttribute RestaurantDto restaurantDto) throws  IOException{
        try{ return restaurantService.UpdateRestaurant(id, restaurantDto);}
        catch (LocationNotFoundException e){return e.getMessage();}
    }

    @PutMapping(value = "/updateLodge/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateLodge(@PathVariable Long id, @ModelAttribute LodgeDto lodgeDto) throws  IOException{
        try{ return lodgeService.UpdateLodge(id, lodgeDto); }
        catch (LocationNotFoundException e){return e.getMessage();}
    }

    @PutMapping(value = "/updateGuestHouse/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String updateGuestHouse(@PathVariable Long id, @ModelAttribute GuestHouseDto guestHouseDto) throws  IOException{
        try{ return guestHouseService.UpdateGuestHouse(id, guestHouseDto);}
        catch (LocationNotFoundException e){return e.getMessage();}
    }

/******************************************  Delete  ******************************************************/
    @DeleteMapping("/deleteCafe/{id}")
    public String deleteCafe(@PathVariable Long id) {
        try { return cafeService.DeleteCafe(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteHotel/{id}")
    public String deleteHotel(@PathVariable Long id){
        try{ return hotelService.DeleteHotel(id);}
        catch (LocationNotFoundException e){ return  e.getMessage();}
    }

    @DeleteMapping("/deleteLodge/{id}")
    public String deleteLodge(@PathVariable Long id){
        try{  return lodgeService.DeleteLodge(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteCamping/{id}")
    public String deleteCamping(@PathVariable Long id){
        try{ return campingService.DeleteCamping(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteRestaurant/{id}")
    public String deleteRestaurant(@PathVariable Long id){
        try{ return restaurantService.DeleteRestaurant(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }

    @DeleteMapping("/deleteGuestHouse/{id}")
    public String deleteGuestHouse(@PathVariable Long id){
        try{ return guestHouseService.DeleteGuestHouse(id);}
        catch (LocationNotFoundException e) {return e.getMessage();}
    }
}
