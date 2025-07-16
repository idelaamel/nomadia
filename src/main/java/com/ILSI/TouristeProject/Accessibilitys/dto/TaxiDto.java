package com.ILSI.TouristeProject.Accessibilitys.dto;

import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class TaxiDto extends AccessibilityDto {

    @NotNull(message = "Taxi number is required")
    @Schema(description = "Number of the Taxi")
    private int taxiNumber;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1 person")
    @Schema(description = "Capacity of the Taxi")
    private int capacity;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TaxiDtoResponse extends AccessibilityDtoResponse {

        private int taxiNumber;
        private int capacity;
    }
}
