package com.ILSI.TouristeProject.Accessibilitys.Service;

import com.ILSI.TouristeProject.Accessibilitys.dto.BusDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface IBusService {

    List<Bus> getAllBus();

    Optional<Bus> findBusByName(String name);

    String addBus(BusDto busDto) throws IOException;

    String updateBus(Long bus_id,BusDto busDto) throws IOException ;

    String deleteBus(Long bus_id);

}
