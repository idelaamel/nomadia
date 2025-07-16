package com.ILSI.TouristeProject.AutreClass.dto;

import com.ILSI.TouristeProject.Activitis.ActivityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
public class StepDto {

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Description is required")
    private String description;

    @Schema( type = "Integer", example = "10")
    @NotNull(message = "Number of days is required")
    @Positive(message = "Number of days must be greater than 0")
    private Integer numberDays;

    @Schema( type = "Integer", example = "10")
    @NotNull(message = "Location ID is required")
    @Positive(message = "Location ID must be a positive number")
    private Long location_id;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class StepDtoResponse {

        private Long id_Step;
        private String title;
        private String description;
        private int numberDays;
        private String itineraryName;
        private String locationName;
        private List<ActivityDto.ActivityDtoResponse> activities;
    }
}
