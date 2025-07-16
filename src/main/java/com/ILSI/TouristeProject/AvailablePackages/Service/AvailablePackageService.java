package com.ILSI.TouristeProject.AvailablePackages.Service;

import com.ILSI.TouristeProject.Accessibilitys.dto.BusDto;
import com.ILSI.TouristeProject.Accessibilitys.model.Bus;
import com.ILSI.TouristeProject.AutreClass.Service.ImageService;
import com.ILSI.TouristeProject.AutreClass.model.Image;
import com.ILSI.TouristeProject.AvailablePackages.Repository.AvailablePackageRepository;
import com.ILSI.TouristeProject.AvailablePackages.dto.AvailablePackagesDto;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import com.ILSI.TouristeProject.UserManagement.Exception.AccessibilityNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AvailablePackageService {

    private final ImageService imageService;
    private final AvailablePackageRepository availablePackageRepository;


    public Optional<AvailablePackage> findAvailablePackageByName(String name) { return availablePackageRepository.findByName (name);}


    public AvailablePackage createNewAvailablePackage(AvailablePackagesDto availablePackagesDto) throws IOException {
        AvailablePackage availablePackage = new AvailablePackage();
        return getAvailablePackage(availablePackagesDto, availablePackage);
    }

    public AvailablePackage updateAvailablePackage(Long id, AvailablePackagesDto availablePackagesDto) throws IOException {
        AvailablePackage existingPackage = availablePackageRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("Package with ID " + id + " not found."));
        existingPackage.getImages().clear();
        return getAvailablePackage(availablePackagesDto, existingPackage);
    }

    public void deleteAvailablePackage(Long id) {
        if (!availablePackageRepository.existsById(id)) {
            throw new EntityNotFoundException("Package with ID " + id + " not found.");
        }
        availablePackageRepository.deleteById(id);
    }

    private AvailablePackage getAvailablePackage(AvailablePackagesDto availablePackagesDto, AvailablePackage availablePackage) throws IOException {
        availablePackage.setName(availablePackagesDto.getName());
        availablePackage.setDescription(availablePackagesDto.getDescription());
        availablePackage.setPrice(availablePackagesDto.getPrice());
        availablePackage.setCapacity(availablePackagesDto.getCapacity());
        availablePackage.setDuration(availablePackagesDto.getDuration());
        availablePackage.setStartDate(availablePackagesDto.getStartDate());
        availablePackage.setEndDate(availablePackagesDto.getEndDate());
        this.addImagesToAvPackage(availablePackage, availablePackagesDto.getImages());
        return availablePackageRepository.save(availablePackage);
    }


    public void addImagesToAvPackage(AvailablePackage avPkg, List<MultipartFile> images) throws IOException {
        for (MultipartFile file : images) {
            String url = imageService.saveImage(file);
            Image image = new Image();
            image.setImageUrl(url);
            avPkg.addImage(image);
        }
    }
}
