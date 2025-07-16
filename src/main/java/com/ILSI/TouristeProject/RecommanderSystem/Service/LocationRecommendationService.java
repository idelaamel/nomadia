package com.ILSI.TouristeProject.RecommanderSystem.Service;

import com.ILSI.TouristeProject.AncillaryServices.model.*;
import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import com.ILSI.TouristeProject.Locations.Amenity.Controller.PublicAmenityController;
import com.ILSI.TouristeProject.Locations.Amenity.Model.*;
import com.ILSI.TouristeProject.Locations.Attraction.Attraction;
import com.ILSI.TouristeProject.Locations.Attraction.Controller.PublicAttractionController;
import com.ILSI.TouristeProject.Locations.Attraction.model.ArtificialAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.CulturalAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.HistoricalAttraction;
import com.ILSI.TouristeProject.Locations.Attraction.model.NaturalAttraction;
import com.ILSI.TouristeProject.Locations.model.Location;
import com.ILSI.TouristeProject.Locations.model.LocationRepository;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesLocation;
import com.ILSI.TouristeProject.RecommanderSystem.Repository.FavoritesLocationRepository;
import com.ILSI.TouristeProject.UserManagement.Repository.UserRepository;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.apache.mahout.cf.taste.impl.model.file.*;
import org.apache.mahout.cf.taste.impl.neighborhood.NearestNUserNeighborhood;
import org.apache.mahout.cf.taste.impl.recommender.GenericUserBasedRecommender;
import org.apache.mahout.cf.taste.impl.similarity.EuclideanDistanceSimilarity;
import org.apache.mahout.cf.taste.model.DataModel;
import org.apache.mahout.cf.taste.neighborhood.UserNeighborhood;
import org.apache.mahout.cf.taste.recommender.RecommendedItem;
import org.apache.mahout.cf.taste.recommender.Recommender;
import org.apache.mahout.cf.taste.similarity.UserSimilarity;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LocationRecommendationService {

    private final FavoritesLocationRepository favoritesRepository;
    private final LocationRepository locationRepository;
    private final UserRepository userRepository;
    private final PublicAmenityController amenityController;
    private final PublicAttractionController attractionController;

    public List<Location> recommenderLocation(Long userId) throws Exception {

        File file = new File("favoritesLocation.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (FavoritesLocation fav : favoritesRepository.findAll()) {
                writer.println(fav.getUser().getId() + "," +
                        fav.getLocation().getId_Location() + ",1.0");
            }
        }

        DataModel model = new FileDataModel(file);
        UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> items = recommender.recommend(userId, 5);
        List<Location> locationList = new ArrayList<>();
        for (RecommendedItem item : items) {
            locationRepository.findById(item.getItemID()).ifPresent(locationList::add);
        }
        return locationList;
    }

    public void addLocationToFavorites(Long id_User, Long id_Location) {
        AppUser user = userRepository.findById(id_User).orElseThrow(() -> new RuntimeException("User Not Found"));
        Location location = locationRepository.findById(id_Location).orElseThrow(() -> new RuntimeException("Location Not Found"));
        boolean exists = favoritesRepository.existsByUserAndLocation(user, location);
        if (exists) {throw new RuntimeException("this Location already in the favorites.");}

        FavoritesLocation favorites = new FavoritesLocation();
        favorites.setUser(user);
        favorites.setLocation(location);
        favoritesRepository.save(favorites);
    }

    public List<Object> getFavoritesLocationByUser(Long userId) {
        List<Long> locationIds = favoritesRepository.findLocationIdsByUserId(userId);

        return locationIds.stream()
                .map(id -> locationRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(location -> {
                    if (location instanceof Amenity) {
                        if (location instanceof Cafe) {
                            return amenityController.convertCafeToDTO((Cafe) location);
                        } else if (location instanceof Camping) {
                            return amenityController.convertCampingToDTO((Camping) location);
                        } else if (location instanceof GuestHouse) {
                            return amenityController.convertGuestHouseToDTO((GuestHouse) location);
                        } else if (location instanceof Hotel) {
                            return amenityController.convertHotelToDTO((Hotel) location);
                        } else if (location instanceof Restaurant) {
                            return amenityController.convertRestaurantToDTO((Restaurant) location);
                        }
                    } else if (location instanceof Attraction) {
                        if (location instanceof ArtificialAttraction) {
                            return attractionController.convertArtificialAttractionToDTO((ArtificialAttraction) location);
                        } else if (location instanceof CulturalAttraction) {
                            return attractionController.convertCulturalAttractionToDTO((CulturalAttraction) location);
                        } else if (location instanceof HistoricalAttraction) {
                            return attractionController.convertHistoricalAttractionToDTO((HistoricalAttraction) location);
                        } else if (location instanceof NaturalAttraction) {
                            return attractionController.convertNaturalAttractionToDTO((NaturalAttraction) location);
                        }
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
