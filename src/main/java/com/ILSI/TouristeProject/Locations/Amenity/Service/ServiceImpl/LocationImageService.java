package com.ILSI.TouristeProject.Locations.Amenity.Service.ServiceImpl;

import com.ILSI.TouristeProject.Accessibilitys.Accessibility;
import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import com.ILSI.TouristeProject.AutreClass.Repository.CityRepository;
import com.ILSI.TouristeProject.AutreClass.Repository.CountryRepository;
import com.ILSI.TouristeProject.AutreClass.Service.ImageService;
import com.ILSI.TouristeProject.AutreClass.model.City;
import com.ILSI.TouristeProject.AutreClass.model.Country;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.Locations.Amenity.Amenity;
import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import com.ILSI.TouristeProject.Locations.Attraction.Attraction;
import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import com.ILSI.TouristeProject.Locations.Dto.LocationDto;
import com.ILSI.TouristeProject.Locations.model.Location;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationImageService {

    private final ImageService imageService;
    private final CountryRepository countryRepository;
    private final CityRepository cityRepository;


    public void addImagesToLocation(Location location, List<MultipartFile> images) throws IOException {
        for (MultipartFile file : images) {
            String url = imageService.saveImage(file);
            Image image = new Image();
            image.setImageUrl(url);
            image.setLocation(location);
            location.addImage(image);
        }
    }

    public void addImagesToAccessibility(Accessibility accessibility, List<MultipartFile> images) throws IOException {
        for (MultipartFile file : images) {
            String url = imageService.saveImage(file);
            Image image = new Image();
            image.setImageUrl(url);
            image.setAccessibility(accessibility);
            accessibility.addImage(image);
        }
    }
    public Pair<Country, City> getOrCreateCountryAndCity(String countryName, String cityName) {
        Optional<Country> existingCountry = countryRepository.findCountryByName(countryName);
        Optional<City> existingCity = cityRepository.findCityByName(cityName);

        Country country = existingCountry.orElseGet(() -> {
            Country newCountry = new Country();
            newCountry.setName(countryName);
            return newCountry;
        });
        City city = existingCity.orElseGet(() -> {
            City newCity = new City();
            newCity.setName(cityName);
            newCity.setCountry(country);
            country.addCity(newCity);
            return newCity;
        });
        if (existingCountry.isEmpty()) countryRepository.save(country);
        if (existingCity.isEmpty()) cityRepository.save(city);
        return Pair.of(country, city);
    }

    public Pair<Country, City> handleLocation(String countryName, String cityName, Location location) {
        Pair<Country, City> result = getOrCreateCountryAndCity(countryName, cityName);
        result.getRight().addLocation(location);
        return result;
    }

    public Pair<Country, City> handleAccessibility(String countryName, String cityName, Accessibility accessibility) {
        return getOrCreateCountryAndCity(countryName, cityName);
    }



    public void getAmenity(AmenityDto amenityDto , Amenity newAmenity) throws IOException {
        newAmenity.setName(amenityDto.getName());
        newAmenity.setDescription(amenityDto.getDescription());
        newAmenity.setLatitude(amenityDto.getLatitude());
        newAmenity.setLongitude(amenityDto.getLongitude());
        newAmenity.setAvailable(amenityDto.isAvailable());
        newAmenity.setPrice(amenityDto.getPrice());
        newAmenity.setOpeningHours(amenityDto.getOpeningHours());
        City city = this.handleLocation(amenityDto.getCountryName(), amenityDto.getCityName(), newAmenity).getRight();
        newAmenity.setCity(city);
        this.addImagesToLocation(newAmenity,amenityDto.getImages());
    }


    public void getAttraction(AttractionDto attractionDto, Attraction newAttraction) throws IOException {
        newAttraction.setName(attractionDto.getName());
        newAttraction.setDescription(attractionDto.getDescription());
        newAttraction.setLatitude(attractionDto.getLatitude());
        newAttraction.setLongitude(attractionDto.getLongitude());
        newAttraction.setEntryFre(attractionDto.getEntryFre());
        newAttraction.setGuideToursAvailable(attractionDto.isGuideToursAvailable());
        City city = this.handleLocation(attractionDto.getCountryName(), attractionDto.getCityName(), newAttraction).getRight();
        newAttraction.setCity(city);
        this.addImagesToLocation(newAttraction, attractionDto.getImages());
    }


    public void getAccessibility(AccessibilityDto accessibilityDto, Accessibility newAccessibility) throws IOException {
        newAccessibility.setName(accessibilityDto.getName());
        newAccessibility.setDescription(accessibilityDto.getDescription());
        newAccessibility.setPrice(accessibilityDto.getPrice());
        newAccessibility.setDepartureTime(accessibilityDto.getDepartureTime());
        newAccessibility.setArrivalTime(accessibilityDto.getArrivalTime());
        City departureCity = this.handleAccessibility(accessibilityDto.getDepartureCountry(), accessibilityDto.getDepartureCity(), newAccessibility).getRight();
        departureCity.addAccessibilityToDepartureCity(newAccessibility);
        City arrivalCity = this.handleAccessibility(accessibilityDto.getArrivalCountry(), accessibilityDto.getArrivalCity(), newAccessibility).getRight();
        arrivalCity.addAccessibilityToArrivalCity(newAccessibility);
        newAccessibility.setDepartureCity(departureCity);
        newAccessibility.setArrivalCity(arrivalCity);
        this.addImagesToAccessibility(newAccessibility, accessibilityDto.getImages());
    }
}
