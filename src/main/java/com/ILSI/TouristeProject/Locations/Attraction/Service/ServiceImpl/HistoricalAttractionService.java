package com.ILSI.TouristeProject.Locations.Attraction.Service.ServiceImpl;

import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl.LocationImageService;
import com.ILSI.TouristeProject.Locations.Attraction.Dto.HistoricalAttractionDto;
import com.ILSI.TouristeProject.Locations.Attraction.Repository.HistoricalAttractionRepository;
import com.ILSI.TouristeProject.Locations.Attraction.Service.IHistoricalAttractionService;
import com.ILSI.TouristeProject.Locations.Attraction.model.HistoricalAttraction;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Exception.LocationNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HistoricalAttractionService implements IHistoricalAttractionService {

    private final HistoricalAttractionRepository historicalAttractionRepository;
    private final LocationImageService locationImageService;

    @Override
    public List<HistoricalAttraction> getAllHistoricalAttraction() {
        return historicalAttractionRepository.findAll();
    }

    @Override
    public Optional<HistoricalAttraction> findHistoricalAttractionByName(String name) {return historicalAttractionRepository.findHistoricalAttractionByName(name);}

    @Override
    public String addHistoricalAttraction(HistoricalAttractionDto historicalAttractionDto) throws IOException {
        Optional<HistoricalAttraction> attraction = historicalAttractionRepository.findHistoricalAttractionByName(historicalAttractionDto.getName());
        if(attraction.isPresent()){
            throw  new LocationAlreadyExistException("Historical Attraction with Name "+historicalAttractionDto.getName()+"Already Exist");
        }
        var newHistoricalAttraction = new HistoricalAttraction();
        getHistoricalAttraction(historicalAttractionDto, newHistoricalAttraction);
        return "Success! Historical Attraction Created successfully";
    }

    @Override
    public String UpdateHistoricalAttraction(Long historicalAttraction_id, HistoricalAttractionDto historicalAttractionDto) throws IOException{
        HistoricalAttraction attraction = historicalAttractionRepository.findById(historicalAttraction_id)
                .orElseThrow(()->new LocationNotFoundException("Historical Attraction with ID = "+ historicalAttraction_id+" Not Found"));
        getHistoricalAttraction( historicalAttractionDto ,attraction);
        return "Success! Historical Attraction Updated successfully";
    }

    @Override
    public String DeleteHistoricalAttraction(Long historicalAttraction_id) {
        HistoricalAttraction attraction = historicalAttractionRepository.findById(historicalAttraction_id)
                .orElseThrow(()->new LocationNotFoundException("Historical Attraction with ID = "+ historicalAttraction_id+" Not Found"));
        historicalAttractionRepository.delete(attraction);
       return "Success! Historical Attraction Deleted successfully";
    }


    private void getHistoricalAttraction(HistoricalAttractionDto historicalAttractionDto, HistoricalAttraction newHistoricalAttraction) throws IOException {
        locationImageService.getAttraction(historicalAttractionDto, newHistoricalAttraction);
        newHistoricalAttraction.setStyle(historicalAttractionDto.getStyle());
        historicalAttractionRepository.save(newHistoricalAttraction);
    }
}
