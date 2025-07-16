package com.ILSI.TouristeProject.RecommanderSystem.Service;


import com.ILSI.TouristeProject.AvailablePackages.Controller.AvailablePackagesController;
import com.ILSI.TouristeProject.AvailablePackages.Repository.AvailablePackageRepository;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAvailablePackage;
import com.ILSI.TouristeProject.RecommanderSystem.Repository.FavoritesAvailablePackageRepository;
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
public class AvailablePackageRecommendationService {

    private final FavoritesAvailablePackageRepository favoritesRepository;
    private final AvailablePackageRepository availablePackageRepository;
    private final UserRepository userRepository;
    private final AvailablePackagesController controller;

    public List<AvailablePackage> recommenderAvailablePackage(Long userId) throws Exception {


        File file = new File("favoritesAvailablePackage.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (FavoritesAvailablePackage fav : favoritesRepository.findAll()) {
                writer.println(fav.getUser().getId() + "," +
                        fav.getAvailablePackage().getId_AvailablePackage()+ ",1.0");
            }
        }

        DataModel model = new FileDataModel(file);
        UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);


        List<RecommendedItem> items = recommender.recommend(userId, 5);
        List<AvailablePackage> availablePackageList = new ArrayList<>();
        for (RecommendedItem item : items) {
            availablePackageRepository.findById(item.getItemID()).ifPresent(availablePackageList::add);
        }
        return availablePackageList;
    }

    public void addAvailablePackageToFavorites(Long id_User, Long id_AvailablePackage) {
        AppUser user = userRepository.findById(id_User).orElseThrow(() -> new RuntimeException("User Not Found"));
        AvailablePackage availablePackage = availablePackageRepository.findById(id_AvailablePackage).orElseThrow(() -> new RuntimeException("Available Package Not Found"));
        boolean exists = favoritesRepository.existsByUserAndAvailablePackage(user, availablePackage);
        if (exists) {throw new RuntimeException("this Available Package already in the favorites.");}

        FavoritesAvailablePackage favorites = new FavoritesAvailablePackage();
        favorites.setUser(user);
        favorites.setAvailablePackage(availablePackage);
        favoritesRepository.save(favorites);
    }

    public List<?> getFavoritesAvailablePackageByUser(Long userId) {
        List<Long> availablePackageIds = favoritesRepository.findAvailablePackageIdsByUserId(userId);

        return availablePackageIds.stream()
                .map(id -> availablePackageRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(controller::convertAvailablePackagesToDTO)
                .collect(Collectors.toList());
    }
}
