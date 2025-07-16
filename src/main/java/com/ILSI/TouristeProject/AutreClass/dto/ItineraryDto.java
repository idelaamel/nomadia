package com.ILSI.TouristeProject.AutreClass.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItineraryDto {

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Name is required")
    private String name;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Description is required")
    private String description;

    @Schema(type = "string", example = "2025-05-28 17:34")
    @NotNull(message = "Start date is required")
    @Future(message = "Start date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @Schema(type = "string", example = "2025-05-28 17:34")
    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Departure City Name is required")
    private String departureCityName;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Departure Country Name is required")
    private String departureCountryName;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Arrival City Name is required")
    private String arrivalCityName;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Arrival Country Name is required")
    private String arrivalCountryName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ItineraryDtoResponse {
        private Long id_Itinerary;
        private String name;
        private String description;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endDate;
        private String departureCityName;
        private String arrivalCityName;
        private String visitTitle;
        private List<StepDto.StepDtoResponse> steps;
    }
}
