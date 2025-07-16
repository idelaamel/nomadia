package com.ILSI.TouristeProject.AvailablePackages.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class AvailablePackagesDto {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the Available Package")
    private String name;

    @NotBlank(message = "Description is required")
    @Schema(description = "Description of the Available Package")
    private String description;

    @NotNull(message = "duration is required")
    @Positive(message = "duration must be greater than 0")
    @Schema(description = "duration of the Available Package")
    private int duration;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Schema(description = "Price of the Available Package for a single person")
    private double price;

    @NotNull(message = "Departure time is required")
    @Future(message = "Departure time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(description = "departure Time of the Available Package format yyyy-mm-dd hh:mm")
    private LocalDateTime startDate;

    @NotNull(message = "Arrival time is required")
    @Future(message = "Arrival time must be in the future")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    @Schema(description = "arrival Time of the Available Package format yyyy-mm-dd hh:mm")
    private LocalDateTime endDate;

    @NotNull(message = "Capacity is required")
    @Min(value = 1, message = "Capacity must be at least 1 person")
    @Schema(description = "Capacity of the Available Package")
    private int capacity;

    @NotEmpty(message = "images is required")
    @Schema(description = "images of the Available Package")
    private List<MultipartFile> images;

    @Data
    public static class AvailablePackagesDtoResponse {

        private Long id_AvailablePackage;
        private String name;
        private String description;
        private int duration;
        private double price;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime startDate;
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
        private LocalDateTime endDate;
        private int capacity;
        private List<String> imageUrls;
    }
}
