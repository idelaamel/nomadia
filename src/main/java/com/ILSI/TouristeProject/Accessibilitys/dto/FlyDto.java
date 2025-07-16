package com.ILSI.TouristeProject.Accessibilitys.dto;

import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class FlyDto extends AccessibilityDto {

    @NotBlank(message = "Name of Airline is required")
    @Schema(description = "Name of the airline")
    private String airline;

    @NotBlank(message = "Departure Airport Name is required")
    @Schema(description = "Name of the departure airport")
    private String departureAirport;

    @NotBlank(message = "Arrival Airport Name is required")
    @Schema(description = "Name of the arrival airport")
    private String arrivalAirport;


    @Data
    @NoArgsConstructor
    public static class FlyDtoResponse extends AccessibilityDtoResponse {

        private String airline;
        private String departureAirport;
        private String arrivalAirport;

    }
}
