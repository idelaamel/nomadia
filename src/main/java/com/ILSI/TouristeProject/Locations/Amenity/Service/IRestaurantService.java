package com.ILSI.TouristeProject.Locations.Amenity.Service;

import com.ILSI.TouristeProject.Locations.Amenity.Dto.RestaurantDto;
import com.ILSI.TouristeProject.Locations.Amenity.Model.Restaurant;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IRestaurantService {


    List<Restaurant> getAllRestaurants();

    Optional<Restaurant> findRestaurantByName(String name);

    String addRestaurant(RestaurantDto restaurantRequest) throws IOException;

    String UpdateRestaurant(Long restaurant_id , RestaurantDto restaurantDto) throws IOException;

    String DeleteRestaurant(Long restaurant_id);
}
