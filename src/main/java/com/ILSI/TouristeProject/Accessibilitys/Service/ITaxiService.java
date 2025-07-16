package com.ILSI.TouristeProject.Accessibilitys.Service;

import com.ILSI.TouristeProject.Accessibilitys.dto.TaxiDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Taxi;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface ITaxiService {

    List<Taxi> getAllTaxis();

    Optional<Taxi> findTaxiByNane(String nane);

    String  addTaxi(TaxiDto taxiDto) throws IOException;

    String updateTaxi(Long taxi_id , TaxiDto taxiDto) throws IOException ;

    String deleteTaxi(Long taxi_id );
}
