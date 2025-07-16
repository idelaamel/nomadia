package com.ILSI.TouristeProject.AncillaryServices.Service;

import com.ILSI.TouristeProject.AncillaryServices.dto.SanitaryDto;
import com.ILSI.TouristeProject.AncillaryServices.model.Sanitary;

import java.util.List;
import java.util.Optional;

public interface ISanitaryService {

    List<Sanitary> findAllSanitary();

    Optional<Sanitary> findSanitaryByName(String sanitaryName);

    String addSanitary(SanitaryDto sanitaryDto);

    String updateSanitary(Long sanitary_id , SanitaryDto sanitaryDto);

    String deleteSanitary(Long sanitary_id);
}
