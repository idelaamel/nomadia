package com.ILSI.TouristeProject.Accessibilitys;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AccessibilityDto {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the Accessibility")
    private String name;

    @NotBlank(message = "Description is required")
    @Schema(description = "Description of the Accessibility")
    private String description;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Schema(description = "Price of the Accessibility for a single person")
    private double price;

    @NotNull(message = "Departure time is required")
    @Future(message = "Departure time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(description = "departure Time of the Accessibility format yyyy-mm-dd hh:mm")
    private LocalDateTime departureTime;

    @NotNull(message = "Arrival time is required")
    @Future(message = "Arrival time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(description = "arrival Time of the Accessibility format yyyy-mm-dd hh:mm")
    private LocalDateTime arrivalTime;

    @NotBlank(message = "Departure city is required")
    @Schema(description = "departure City of the Accessibility")
    private String departureCity;

    @NotBlank(message = "Arrival city is required")
    @Schema(description = "Arrival City of the Accessibility")
    private String arrivalCity;

    @NotBlank(message = "Departure country is required")
    @Schema(description = "departure Country of the Accessibility")
    private String departureCountry;

    @NotBlank(message = "Arrival country is required")
    @Schema(description = "arrival country of the Accessibility")
    private String arrivalCountry;

    @NotEmpty(message = "images is required")
    @Schema(description = "images of the Accessibility")
    private List<MultipartFile> images;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AccessibilityDtoResponse {

        private Long id_Accessibility;
        private String name;
        private String description;
        private double price;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime departureTime;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime arrivalTime;
        private String departureCity;
        private String arrivalCity;
        private String departureCountry;
        private String arrivalCountry;
        private List<String> images;

    }
}
