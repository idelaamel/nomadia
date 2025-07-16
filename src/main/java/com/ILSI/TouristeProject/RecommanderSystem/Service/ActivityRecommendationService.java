package com.ILSI.TouristeProject.RecommanderSystem.Service;


import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;
import com.ILSI.TouristeProject.Activitis.Activity;
import com.ILSI.TouristeProject.Activitis.ActivityRepository;
import com.ILSI.TouristeProject.Activitis.Controller.PublicActivityController;
import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;
import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;
import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;
import com.ILSI.TouristeProject.Activitis.model.TraditionalActivity;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesActivity;
import com.ILSI.TouristeProject.RecommanderSystem.Repository.FavoritesActivityRepository;
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
public class ActivityRecommendationService {

    private final FavoritesActivityRepository favoritesRepository;
    private final ActivityRepository activityRepository;
    private final UserRepository userRepository;
    private final PublicActivityController controller;

    public List<Activity> recommenderActivity(Long userId) throws Exception {
        //  Créer fichier CSV temporaire pour Mahout
        File file = new File("favoritesActivity.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (FavoritesActivity fav : favoritesRepository.findAll()) {
                writer.println(fav.getUser().getId() + "," +
                        fav.getActivity().getId_Activity() + ",1.0");
            }
        }

        DataModel model = new FileDataModel(file);
        UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> items = recommender.recommend(userId, 5);
        List<Activity> activityList = new ArrayList<>();
        for (RecommendedItem item : items) {
            activityRepository.findById(item.getItemID()).ifPresent(activityList::add);
        }
        return activityList;
    }

    public void addActivityToFavorites(Long id_User, Long id_Activity) {
        AppUser user = userRepository.findById(id_User).orElseThrow(() -> new RuntimeException("User Not Found"));
        Activity activity = activityRepository.findById(id_Activity).orElseThrow(() -> new RuntimeException("Activity Not Found"));
        boolean exists = favoritesRepository.existsByUserAndActivity(user, activity);
        if (exists) {throw new RuntimeException("this Activity already in the favorites.");}

        FavoritesActivity favorites = new FavoritesActivity();
        favorites.setUser(user);
        favorites.setActivity(activity);
        favoritesRepository.save(favorites);
    }


    public List<?> getFavoritesActivityByUser(Long userId) {
        List<Long> activityIds = favoritesRepository.findActivityIdsByUserId(userId);

        return activityIds.stream()
                .map(id -> activityRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(activity -> {
                    if (activity instanceof AdventureActivity) {
                        return controller.convertAdventureActivityToDTO((AdventureActivity) activity);
                    } else if (activity instanceof CulturalActivity) {
                        return controller.convertCulturalActivityToDTO((CulturalActivity) activity);
                    }  else if (activity instanceof SportiveActivity) {
                        return controller.convertSportiveActivityToDTO((SportiveActivity) activity);
                    }else {
                        return controller.convertTraditionalActivityToDTO((TraditionalActivity) activity);
                    }
                })
                .collect(Collectors.toList());
    }
}
