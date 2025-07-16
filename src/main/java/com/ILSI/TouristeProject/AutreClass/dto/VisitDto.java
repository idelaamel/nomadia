package com.ILSI.TouristeProject.AutreClass.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class VisitDto {

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Title is required")
    private String title;

    @Schema( type = "string", example = " ")
    @NotBlank(message = "Description is required")
    private String description;

    @Schema(type = "string", example = "2025-05-28 17:34")
    @NotNull(message = "Start date is required")
    @FutureOrPresent(message = "Start date must be in the present or future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime startDate;

    @Schema(type = "string", example = "2025-05-28 17:34")
    @NotNull(message = "End date is required")
    @Future(message = "End date must be in the future")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime endDate;

    @Schema(type = "integer", example = "10")
    @NotNull(message = "Number of days is required")
    @Positive(message = "Number of days must be greater than 0")
    private Integer numberDays;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class VisitDtoResponse {

        private Long id_Visit;
        private String title;
        private String description;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endDate;
        private int numberDays;
        private String username;
        private List<ItineraryDto.ItineraryDtoResponse> Itinerary;
    }
}
