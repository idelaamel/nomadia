package com.ILSI.TouristeProject.Accessibilitys.Service;


import com.ILSI.TouristeProject.Accessibilitys.dto.FlyDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Fly;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IFlayService {


    List<Fly> getAllFly();

    Optional<Fly> findFlyByName(String name);

    String addFly(FlyDto flyDto) throws IOException;

    String updateFly(Long fly_id,FlyDto flyDto) throws IOException ;

    String deleteFly(Long fly_id);
}
