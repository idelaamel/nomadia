package com.ILSI.TouristeProject.UserManagement.Controller;

import com.ILSI.TouristeProject.AvailablePackages.Controller.AvailablePackagesController;
import com.ILSI.TouristeProject.AvailablePackages.Repository.AvailablePackageRepository;
import com.ILSI.TouristeProject.AvailablePackages.Service.AvailablePackageService;
import com.ILSI.TouristeProject.AvailablePackages.dto.AvailablePackagesDto;
import com.ILSI.TouristeProject.AvailablePackages.model.AvailablePackage;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/admin")
@Tag(name="Available Packages Management By Admin", description = "Create, Update and Delete Available Package")
public class AdminAvailablePackageController {

    private final AvailablePackageService availablePackageService;
    private final AvailablePackagesController controller;

    @PostMapping(value = "/available-packages", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AvailablePackagesDto.AvailablePackagesDtoResponse> createAvailablePackage(@Valid @ModelAttribute AvailablePackagesDto availablePackagesDto) throws IOException {
        AvailablePackage createdAvailablePackage = availablePackageService.createNewAvailablePackage(availablePackagesDto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(controller.convertAvailablePackagesToDTO(createdAvailablePackage));
    }

    @PutMapping(value = "/available-packages/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<AvailablePackagesDto.AvailablePackagesDtoResponse> updateAvailablePackage(@PathVariable Long id, @Valid @ModelAttribute AvailablePackagesDto availablePackagesDto) throws IOException {
        AvailablePackage updatedAvailablePackage = availablePackageService.updateAvailablePackage(id, availablePackagesDto);
        return ResponseEntity.ok(controller.convertAvailablePackagesToDTO(updatedAvailablePackage));
    }

    @DeleteMapping("/available-packages/{id}")
    public ResponseEntity<String> deleteAvailablePackage(@PathVariable Long id) {
        availablePackageService.deleteAvailablePackage(id);
        return ResponseEntity.ok("Available_Package deleted successfully.");
    }


}
