package com.ILSI.TouristeProject.Activitis.Service.ServiceImpl;

import com.ILSI.TouristeProject.Activitis.Dto.SportiveActivityDto;
import com.ILSI.TouristeProject.Activitis.Repository.SportiveActivityRepository;
import com.ILSI.TouristeProject.Activitis.Service.ISportiveActivityService;
import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class SportiveActivityService implements ISportiveActivityService {

    private final SportiveActivityRepository sportiveActivityRepository;
    private final ActivityService activityService;
    @Override
    public List<SportiveActivity> getAllSportiveActivity() {return sportiveActivityRepository.findAll();}

    @Override
    public Optional<SportiveActivity> findSportiveActivityByName(String name) {return sportiveActivityRepository.findSportiveActivityByName(name);}

    @Override
    public String addSportiveActivity(SportiveActivityDto activityDto) throws IOException {
        Optional<SportiveActivity> activity = sportiveActivityRepository.findSportiveActivityByName(activityDto.getName());
        if (activity.isPresent()) {
            throw new ActivityAlreadyExistException("Sportive Activity with Name"+activityDto.getName()+"Already Exist");
        }
        this.getSportiveActivity(activityDto, new SportiveActivity());
        return "Success! Sportive Activity Created successfully";
    }

    @Override
    public String updateSportiveActivity(Long activity_id, SportiveActivityDto activityDto) throws IOException {
        SportiveActivity activity = sportiveActivityRepository.findById(activity_id).orElseThrow(()->new ActivityNotFoundException("Sportive Activity with ID"+activity_id+"Not Found"));
        this.getSportiveActivity(activityDto, activity);
        return "Success! Sportive Activity Updated successfully";
    }

    @Override
    public String deleteSportiveActivity(Long activity_id) {
        SportiveActivity activity = sportiveActivityRepository.findById(activity_id).orElseThrow(()->new ActivityNotFoundException("Sportive Activity with ID"+activity_id+"Not Found"));
        sportiveActivityRepository.delete(activity);
        return "Success! Sportive Activity Deleted successfully";
    }

    private void getSportiveActivity(SportiveActivityDto activityDto, SportiveActivity activity) throws IOException {
        activityService.getActivity(activityDto,activity);
        activity.setTypeSport(activityDto.getTypeSport());
        sportiveActivityRepository.save(activity);
    }
}
