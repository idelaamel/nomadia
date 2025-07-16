package com.ILSI.TouristeProject.Activitis.Service.ServiceImpl;

import com.ILSI.TouristeProject.Activitis.Dto.CulturalActivityDto;
import com.ILSI.TouristeProject.Activitis.Repository.CulturalActivityRepository;
import com.ILSI.TouristeProject.Activitis.Service.ICulturalActivityService;
import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;
import com.ILSI.TouristeProject.UserManagement.Exception.ActivityAlreadyExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
@Service
@RequiredArgsConstructor
public class CulturalActivityService implements ICulturalActivityService {

    private final CulturalActivityRepository culturalActivityRepository;
    private final ActivityService activityService;

    @Override
    public List<CulturalActivity> getAllCulturalActivity() {return culturalActivityRepository.findAll();}

    @Override
    public Optional<CulturalActivity> findCulturalActivityByName(String name) {return culturalActivityRepository.findCulturalActivityByName(name);}

    @Override
    public String addCulturalActivity(CulturalActivityDto activityDto) throws IOException {
        Optional<CulturalActivity> activity = culturalActivityRepository.findCulturalActivityByName(activityDto.getName());
        if (activity.isPresent()) {
            throw new ActivityAlreadyExistException("Cultural Activity with Name"+activityDto.getName()+"Already Exist");
        }
        this.getCulturalActivity(activityDto, new CulturalActivity());
        return "Success! Cultural Activity Created successfully";
    }
    @Override
    public String updateCulturalActivity(Long activity_id, CulturalActivityDto activityDto) throws IOException {
        CulturalActivity activity = culturalActivityRepository.findById(activity_id).orElseThrow(()-> new ActivityAlreadyExistException("Cultural Activity with ID = "+ activity_id +" Not Found"));
        this.getCulturalActivity(activityDto, activity);
        return "Success! Cultural Activity Updated successfully";
    }
    @Override
    public String deleteCulturalActivity(Long activity_id) {
        CulturalActivity activity = culturalActivityRepository.findById(activity_id).orElseThrow(()-> new ActivityAlreadyExistException("Cultural Activity with ID = "+ activity_id +" Not Found"));
        culturalActivityRepository.delete(activity);
        return "Success! Cultural Activity Deleted successfully";
    }

    private void getCulturalActivity(CulturalActivityDto activityDto, CulturalActivity activity) throws IOException {
        activityService.getActivity(activityDto,activity);
        activity.setTraditionAssociated(activityDto.getTraditionAssociated());
        culturalActivityRepository.save(activity);
    }
}
