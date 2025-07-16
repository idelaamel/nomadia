package com.ILSI.TouristeProject.Locations.Amenity.Dto;

import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CampingDto extends AmenityDto {

    @NotNull(message = "Capacity of the camping is required")
    @Min(value = 1, message = "Capacity must be at least 1 person")
    @Schema(description = "Capacity of the camping")
    private int capacity;
    private boolean ElectricityAvailability;
    private boolean SanitaryAvailability;
    private boolean hasWaterSupply;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CampingDtoResponse extends AmenityDtoResponse {

        private int capacity; //ors
        private boolean ElectricityAvailability;
        private boolean SanitaryAvailability;
        private boolean hasWaterSupply;

    }
}
