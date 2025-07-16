package com.ILSI.TouristeProject.Activitis.Service;

import com.ILSI.TouristeProject.Activitis.Dto.AdventureActivityDto;
import com.ILSI.TouristeProject.Activitis.model.AdventureActivity;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IAdventureActivityService {

    /// you can define the signature of your methods her

    List<AdventureActivity> getAllAdventureActivity();

    Optional<AdventureActivity> findAdventureActivityByName(String name);

    String addAdventureActivity(AdventureActivityDto activityDto) throws IOException;

    String updateAdventureActivity(Long activity_id,AdventureActivityDto activityDto) throws IOException ;

    String deleteAdventureActivity(Long activity_id);
}
