package com.ILSI.TouristeProject.Activitis;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class ActivityDto {

    @NotBlank(message = "Name is required")
    @Schema(description = "Name of the Activity")
    private String name;

    @NotBlank(message = "Description is required")
    @Schema(description = "Description of the Activity")
    private String description;

    @NotNull(message = "age limit is required")
    @Positive(message = "Age must be greater than 5")
    @Schema(description = "age limit required to this Activity")
    private int ageLimit;

    @NotBlank(message = "duration is required")
    @Schema(description = "duration of this Activity")
    private String duration;

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    @Schema(description = "Price of the Activity for a single person")
    private double price;

    @NotEmpty(message = "images is required")
    @Schema(description = "images of the Activity")
    private List<MultipartFile> images;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ActivityDtoResponse {

        private Long id_Activity;
        private String name;
        private String description;
        private int ageLimit;
        private String duration;
        private double price;
        private List<String> imagesUrls;

    }
}
