package com.ILSI.TouristeProject.RecommanderSystem.Service;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityRepository;
import com.ILSI.TouristeProject.Accessibilitys.Controller.PublicAccessibilityController;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAccessibility;
import com.ILSI.TouristeProject.RecommanderSystem.Repository.FavoritesAccessibilityRepository;
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
public class AccessibilityRecommendationService {

    private final FavoritesAccessibilityRepository favoritesAccessibilityRepository;
    private final AccessibilityRepository accessibilityRepository;
    private final UserRepository userRepository;
    private final PublicAccessibilityController controller;

    public List<Accessibility> recommenderAccessibility(Long userId) throws Exception {
        File file = new File("favoritesAccessibility.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (FavoritesAccessibility fav : favoritesAccessibilityRepository.findAll()) {
                writer.println(fav.getUser().getId() + "," +
                        fav.getAccessibility().getId_Accessibility() + ",1.0");
            }
        }

        DataModel model = new FileDataModel(file);
        UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> items = recommender.recommend(userId, 5);
        List<Accessibility> accessibilityList = new ArrayList<>();
        for (RecommendedItem item : items) {
            accessibilityRepository.findById(item.getItemID()).ifPresent(accessibilityList::add);
        }
        return accessibilityList;
    }


    public void addAccessibilityToFavorites(Long id_User, Long id_Accessibility) {
        AppUser user = userRepository.findById(id_User).orElseThrow(() -> new RuntimeException("User Not Found"));
        Accessibility accessibility = accessibilityRepository.findById(id_Accessibility).orElseThrow(() -> new RuntimeException("Accessibility Not Found"));
        boolean exists = favoritesAccessibilityRepository.existsByUserAndAccessibility(user, accessibility);
        if (exists) {throw new RuntimeException("this accessibility already in the Favorites.");}

        FavoritesAccessibility favorites = new FavoritesAccessibility();
        favorites.setUser(user);
        favorites.setAccessibility(accessibility);
        favoritesAccessibilityRepository.save(favorites);
    }


    public List<?> getFavoritesAccessibilityByUser(Long userId) {
        List<Long> accessibilityIds = favoritesAccessibilityRepository.findAccessibilityIdsByUserId(userId);

        return accessibilityIds.stream()
                .map(id -> accessibilityRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(accessibility -> {
                    if (accessibility instanceof Taxi) {
                        return controller.convertTaxiToDTO((Taxi) accessibility);
                    } else if (accessibility instanceof Bus) {
                        return controller.convertBusToDTO((Bus) accessibility);
                    } else {
                        return controller.convertFlyToDTO((Fly) accessibility);
                    }
                })
                .collect(Collectors.toList());
    }
}
