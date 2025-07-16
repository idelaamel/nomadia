package com.ILSI.TouristeProject.Activitis.Service;

import com.ILSI.TouristeProject.Activitis.Dto.CulturalActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.SportiveActivityDto;
import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;
import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ISportiveActivityService {

    /// you can define the signature of your methods her

    List<SportiveActivity> getAllSportiveActivity();

    Optional<SportiveActivity> findSportiveActivityByName(String name);

    String addSportiveActivity(SportiveActivityDto activityDto) throws IOException;

    String updateSportiveActivity(Long activity_id,SportiveActivityDto activityDto) throws IOException ;

    String deleteSportiveActivity(Long activity_id);
}
