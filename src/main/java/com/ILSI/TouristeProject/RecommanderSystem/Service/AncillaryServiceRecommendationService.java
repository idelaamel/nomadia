package com.ILSI.TouristeProject.RecommanderSystem.Service;

import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;
import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;
import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;
import com.ILSI.TouristeProject.Activitis.model.TraditionalActivity;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryService;
import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceRepository;
import com.ILSI.TouristeProject.AncillaryServices.Controller.PublicAncillaryServiceController;
import com.ILSI.TouristeProject.AncillaryServices.model.*;
import com.ILSI.TouristeProject.RecommanderSystem.Model.FavoritesAncillaryService;
import com.ILSI.TouristeProject.RecommanderSystem.Repository.FavoritesAncillaryServiceRepository;
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
public class AncillaryServiceRecommendationService {

    private final FavoritesAncillaryServiceRepository favoritesRepository;
    private final AncillaryServiceRepository ancillaryServiceRepository;
    private final UserRepository userRepository;
    private final PublicAncillaryServiceController controller;

    public List<AncillaryService> recommenderAncillaryService(Long userId) throws Exception {
        File file = new File("favoritesAncillaryService.csv");
        try (PrintWriter writer = new PrintWriter(file)) {
            for (FavoritesAncillaryService fav : favoritesRepository.findAll()) {
                writer.println(fav.getUser().getId() + "," +
                        fav.getAncillaryService().getId_AncillaryService() + ",1.0");
            }
        }

        DataModel model = new FileDataModel(file);
        UserSimilarity similarity = new EuclideanDistanceSimilarity(model);
        UserNeighborhood neighborhood = new NearestNUserNeighborhood(3, similarity, model);
        Recommender recommender = new GenericUserBasedRecommender(model, neighborhood, similarity);

        List<RecommendedItem> items = recommender.recommend(userId, 5);
        List<AncillaryService> ancillaryServiceList = new ArrayList<>();
        for (RecommendedItem item : items) {
            ancillaryServiceRepository.findById(item.getItemID()).ifPresent(ancillaryServiceList::add);
        }
        return ancillaryServiceList;
    }

    public void addAncillaryServiceToFavorites(Long id_User, Long id_AncillaryService) {
        AppUser user = userRepository.findById(id_User).orElseThrow(() -> new RuntimeException("User Not Found"));
        AncillaryService ancillaryService = ancillaryServiceRepository.findById(id_AncillaryService).orElseThrow(() -> new RuntimeException("Ancillary Service Not Found"));
        boolean exists = favoritesRepository.existsByUserAndAncillaryService(user, ancillaryService);
        if (exists) {throw new RuntimeException("this Ancillary Service already in the favorites.");}

        FavoritesAncillaryService favorites = new FavoritesAncillaryService();
        favorites.setUser(user);
        favorites.setAncillaryService(ancillaryService);
        favoritesRepository.save(favorites);
    }

    public List<?> getFavoritesAncillaryServiceByUser(Long userId) {
        List<Long> activityIds = favoritesRepository.findAncillaryServiceIdsByUserId(userId);

        return activityIds.stream()
                .map(id -> ancillaryServiceRepository.findById(id).orElse(null))
                .filter(Objects::nonNull)
                .map(service -> {
                    if (service instanceof Administrative) {
                        return controller.convertAdServiceToDto((Administrative) service);
                    } else if (service instanceof Bank) {
                        return controller.convertBankToDto((Bank) service);
                    }  else if (service instanceof CarAgency) {
                        return controller.convertCarAgencyToDto((CarAgency) service);
                    } else if (service instanceof Sanitary) {
                        return controller.convertSanitaryToDto((Sanitary) service);
                    } else if (service instanceof TourGuide) {
                        return controller.convertTourGuideToDto((TourGuide) service);
                    }else {
                        return controller.convertTravelAgencyToDto((TravelAgency) service);
                    }
                })
                .collect(Collectors.toList());
    }
}
