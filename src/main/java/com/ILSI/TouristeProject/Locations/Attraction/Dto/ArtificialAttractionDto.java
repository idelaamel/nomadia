package com.ILSI.TouristeProject.Locations.Attraction.Dto;

import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArtificialAttractionDto extends AttractionDto {

    @NotBlank(message = "Year of build of the Artificial Attraction is required")
    @Schema(description = "Year of build of the Artificial Attraction Ex: 1911")
    private String yearBuild;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class ArtificialAttractionDtoResponse extends AttractionDtoResponse {
        private String yearBuild;
    }
}
