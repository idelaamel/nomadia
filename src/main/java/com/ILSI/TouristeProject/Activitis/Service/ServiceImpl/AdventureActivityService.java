package com.ILSI.TouristeProject.Activitis.Service.ServiceImpl;

import com.ILSI.TouristeProject.Activitis.Dto.AdventureActivityDto;
import com.ILSI.TouristeProject.Activitis.Repository.AdventureActivityRepository;
import com.ILSI.TouristeProject.Activitis.Service.IAdventureActivityService;
import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdventureActivityService implements IAdventureActivityService {

    private final AdventureActivityRepository adventureactivityRepository;
    private final ActivityService activityService;

    @Override
    public List<AdventureActivity> getAllAdventureActivity() { return adventureactivityRepository.findAll();}

    @Override
    public Optional<AdventureActivity> findAdventureActivityByName(String name) {return adventureactivityRepository.findAdventureActivityByName(name);}

    @Override
    public String addAdventureActivity(AdventureActivityDto activityDto) throws IOException {
        Optional<AdventureActivity> activity = adventureactivityRepository.findAdventureActivityByName(activityDto.getName());
        if (activity.isPresent()) {
            throw new ActivityAlreadyExistException("Adventure Activity with Name"+activityDto.getName()+"Already Exist");
        }
        this.getAdventureActivity(activityDto, new AdventureActivity());
        return "Success! Adventure Activity Created successfully";
    }

    @Override
    public String updateAdventureActivity(Long activity_id, AdventureActivityDto activityDto) throws IOException {
        AdventureActivity activity = adventureactivityRepository.findById(activity_id).orElseThrow(()-> new ActivityNotFoundException("Adventure Activity with ID = "+ activity_id +" Not Found"));
        this.getAdventureActivity(activityDto, activity);
        return "Success! Adventure Activity Updated successfully";
    }

    @Override
    public String deleteAdventureActivity(Long activity_id) {
        AdventureActivity activity = adventureactivityRepository.findById(activity_id).orElseThrow(()-> new ActivityNotFoundException("Adventure Activity with ID = "+ activity_id +" Not Found"));
        adventureactivityRepository.delete(activity);
            return "Success! Adventure Activity Deleted successfully";
    }

    private void getAdventureActivity(AdventureActivityDto activityDto, AdventureActivity activity) throws IOException {
        activityService.getActivity(activityDto, activity);
        activity.setTerrainType(activityDto.getTerrainType());
        activity.setAgeRestriction(activityDto.getAgeRestriction());
        adventureactivityRepository.save(activity);
    }
}
