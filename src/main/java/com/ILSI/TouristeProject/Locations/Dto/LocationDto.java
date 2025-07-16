package com.ILSI.TouristeProject.Locations.Dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationDto {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the Location")
    private String name;

    @NotBlank(message = "Description is required")
    @Schema(description = "Description of the Location")
    private String description;

    @NotBlank(message = "Country of the Location is required")
    @Schema(description = "Country of the Location")
    private String countryName;

    @NotBlank(message = "City of the Location is required")
    @Schema(description = "City of the Location")
    private String cityName;

    @NotNull(message = "longitude is required")
    @Schema(description = "Latitude of the Location Ex: 00.00")
    private double longitude;

    @NotNull(message = "latitude is required")
    @Schema(description = "Longitude of the Location Ex: 00.00")
    private double latitude;

    @NotEmpty(message = "images is required")
    @Schema(description = "images of the Location")
    private List<MultipartFile> images;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LocationDtoResponse {

        private Long id_Location;
        private String name;
        private String description;
        private String CountryName;
        private String CityName;
        private double Longitude;
        private double Latitude;
        private List<String> imageUrls;

    }
}

