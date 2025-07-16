package com.ILSI.TouristeProject.Activitis.Service;

import com.ILSI.TouristeProject.Activitis.Dto.AdventureActivityDto;
import com.ILSI.TouristeProject.Activitis.Dto.CulturalActivityDto;
import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;
import com.ILSI.TouristeProject.Activitis.model.CulturalActivity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ICulturalActivityService {

    /// you can define the signature of your methods her

    List<CulturalActivity> getAllCulturalActivity();

    Optional<CulturalActivity> findCulturalActivityByName(String name);

    String addCulturalActivity(CulturalActivityDto activityDto) throws IOException;

    String updateCulturalActivity(Long activity_id,CulturalActivityDto activityDto) throws IOException ;

    String deleteCulturalActivity(Long activity_id);
}
