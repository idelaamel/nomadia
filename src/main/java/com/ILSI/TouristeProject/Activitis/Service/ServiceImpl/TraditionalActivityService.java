package com.ILSI.TouristeProject.Activitis.Service.ServiceImpl;

import com.ILSI.TouristeProject.Activitis.Dto.TraditionalActivityDto;
import com.ILSI.TouristeProject.Activitis.Repository.TraditionalActivityRepository;
import com.ILSI.TouristeProject.Activitis.Service.ITraditionalActivityService;
import com.ILSI.TouristeProject.Activitis.model.TraditionalActivity;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class TraditionalActivityService implements ITraditionalActivityService {

    private final TraditionalActivityRepository traditionalActivityRepository;
    private final ActivityService activityService;

    @Override
    public List<TraditionalActivity> getAllTraditionalActivity() {return traditionalActivityRepository.findAll();}

    @Override
    public Optional<TraditionalActivity> findTraditionalActivityByName(String name) {return traditionalActivityRepository.findTraditionalActivityByName(name);}

    @Override
    public String addTraditionalActivity(TraditionalActivityDto activityDto) throws IOException {
        Optional<TraditionalActivity> activity = traditionalActivityRepository.findTraditionalActivityByName(activityDto.getName());
        if (activity.isPresent()) {
            throw new ActivityAlreadyExistException("Traditional Activity with Name"+activityDto.getName()+"Already Exist");
        }
        this.getTraditionalActivity(activityDto, new TraditionalActivity());
        return "Success! Traditional Activity Created successfully";
    }

    @Override
    public String updateTraditionalActivity(Long activity_id, TraditionalActivityDto activityDto) throws IOException {
        TraditionalActivity activity = traditionalActivityRepository.findById(activity_id).orElseThrow(()-> new ActivityNotFoundException("Traditional Activity with ID = "+ activity_id +"  Not Found"));
        this.getTraditionalActivity(activityDto, activity);
        return "Success! Traditional Activity Updated successfully";
    }

    @Override
    public String deleteTraditionalActivity(Long activity_id) {
        TraditionalActivity activity = traditionalActivityRepository.findById(activity_id).orElseThrow(()-> new ActivityNotFoundException("Traditional Activity with ID = "+ activity_id +"  Not Found"));
        traditionalActivityRepository.delete(activity);
        return "Success! Traditional Activity Deleted successfully";
    }

    private void getTraditionalActivity(TraditionalActivityDto activityDto, TraditionalActivity activity) throws IOException {
        activityService.getActivity(activityDto,activity);
        activity.setCraftType(activityDto.getCraftType());
        traditionalActivityRepository.save(activity);
     }
}