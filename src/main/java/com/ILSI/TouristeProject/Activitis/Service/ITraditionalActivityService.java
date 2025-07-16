package com.ILSI.TouristeProject.Activitis.Service;

import com.ILSI.TouristeProject.Activitis.Dto.SportiveActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.TraditionalActivityDto;
import com.ILSI.TouristeProject.Activitis.model.SportiveActivity;
import com.ILSI.TouristeProject.Activitis.model.TraditionalActivity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ITraditionalActivityService {

    /// you can define the signature of your methods her

    List<TraditionalActivity> getAllTraditionalActivity();

    Optional<TraditionalActivity> findTraditionalActivityByName(String name);

    String addTraditionalActivity(TraditionalActivityDto activityDto) throws IOException;

    String updateTraditionalActivity(Long activity_id,TraditionalActivityDto activityDto) throws IOException ;

    String deleteTraditionalActivity(Long activity_id);
}
