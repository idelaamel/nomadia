package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Dto.RestaurantDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Lodge;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Restaurant;
import com.ILSI.TouristeProject.Locations.Amenity.Repository.RestaurantRepository;
import com.ILSI.TouristeProject.Locations.Amenity.Service.IRestaurantService;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class RestaurantService implements IRestaurantService {


    private final RestaurantRepository restaurantRepository;
    private final LocationImageService locationImageService;


    @Override
    public List<Restaurant> getAllRestaurants() {
        return restaurantRepository.findAll();
    }


    @Override
    public Optional<Restaurant> findRestaurantByName(String name) {
        return restaurantRepository.findByName(name);
    }


    @Override
    public String addRestaurant(RestaurantDto restaurantRequest) throws IOException {
        Optional<Restaurant> restaurant = restaurantRepository.findByName(restaurantRequest.getName());
        if(restaurant.isPresent()){
            throw new LocationAlreadyExistException("Restaurant with Name "+restaurantRequest.getName()+" Already Exist");
        }
        var newRestaurant = new Restaurant();
        getRestaurant(restaurantRequest, newRestaurant);
        return "Success! Restaurant Created successfully";

    }

    @Override
    public String UpdateRestaurant(Long restaurant_id, RestaurantDto restaurantDto) throws IOException {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(()-> new LocationNotFoundException("Restaurant with ID = "+ restaurant_id +" Not Found"));
        getRestaurant(restaurantDto, existingRestaurant);
        return "Success! Restaurant Updated successfully";
    }

    @Override
    public String DeleteRestaurant(Long restaurant_id) {
        Restaurant existingRestaurant = restaurantRepository.findById(restaurant_id)
                .orElseThrow(()-> new LocationNotFoundException("Restaurant with ID = "+ restaurant_id +" Not Found"));
        restaurantRepository.delete(existingRestaurant);
        return "Success! Restaurant deleted successfully";
    }

    private void getRestaurant(RestaurantDto restaurantRequest, Restaurant newRestaurant) throws IOException {
        locationImageService.getAmenity(restaurantRequest, newRestaurant);
        newRestaurant.setTypeCuisine(restaurantRequest.getTypeCuisine());
        newRestaurant.setMenu(restaurantRequest.getMenu());
        restaurantRepository.save(newRestaurant);
    }
}
