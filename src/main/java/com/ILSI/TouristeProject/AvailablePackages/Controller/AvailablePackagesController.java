package com.ILSI.TouristeProject.AvailablePackages.Controller;

import com.ILSI.TouristeProject.Accessibilitys.dto.BusDto;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.AvailablePackages.Repository.AvailablePackageRepository;
import com.ILSI.TouristeProject.AvailablePackages.Service.AvailablePackageService;
import com.ILSI.TouristeProject.AvailablePackages.dto.AvailablePackagesDto;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/public")
@Tag(name = " Explore All Available Packages By Users Her ")
public class AvailablePackagesController {

    private final AvailablePackageService availablePackageService;
    private final AvailablePackageRepository availablePackageRepository;



    @GetMapping("/available-packages/{id}")
    public ResponseEntity<?> findAvailablePackageById(@PathVariable Long id) {
        Optional<AvailablePackage> availablePackage = availablePackageRepository.findById(id);
        if (availablePackage.isPresent()) {
            return ResponseEntity.ok(this.convertAvailablePackagesToDTO(availablePackage.get()));
        } else {return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", "Available package with ID " + id + " not found"));
        }
    }

    @GetMapping("/getAll/available_Package")
    public List<AvailablePackagesDto.AvailablePackagesDtoResponse> getAllAvailablePackage() throws IOException {
        return availablePackageRepository.findAll().stream().map(this::convertAvailablePackagesToDTO).toList();
    }


    public AvailablePackagesDto.AvailablePackagesDtoResponse convertAvailablePackagesToDTO(AvailablePackage availablePackage) {
        AvailablePackagesDto.AvailablePackagesDtoResponse response = new AvailablePackagesDto.AvailablePackagesDtoResponse();
        response.setId_AvailablePackage(availablePackage.getId_AvailablePackage());
        response.setName(availablePackage.getName());
        response.setDescription(availablePackage.getDescription());
        response.setPrice(availablePackage.getPrice());
        response.setDuration(availablePackage.getDuration());
        response.setCapacity(availablePackage.getCapacity());
        response.setStartDate(availablePackage.getStartDate());
        response.setEndDate(availablePackage.getEndDate());
        response.setImageUrls(availablePackage.getImages().stream().map(Image::getImageUrl).toList());
        return response;
    }
}
