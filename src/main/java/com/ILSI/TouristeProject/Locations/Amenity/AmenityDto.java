package com.ILSI.TouristeProject.Locations.Amenity;

import com.ILSI.TouristeProject.Locations.Dto.LocationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class AmenityDto extends LocationDto {

    @Positive(message = "Price must be greater than 0")
    @Schema(description = "Price of the Amenity")
    private double price;

    private boolean isAvailable;

    @NotBlank(message = "Opening Hours is required")
    @Schema(description = "Opening Hours of the Amenity Ex: from 00:00 to 00:00")
    private String openingHours;



    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AmenityDtoResponse extends LocationDtoResponse {

        private double price;
        private boolean isAvailable;
        private String openingHours;
    }
}
