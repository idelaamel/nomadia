package com.ILSI.TouristeProject.Locations.Attraction.Dto;

import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@NoArgsConstructor
public class CulturalAttractionDto extends AttractionDto {

    @NotBlank(message = "Year of build of the Cultural Attraction is required")
    @Schema(description = "Year of build of the Cultural Attraction Ex: 1911")
    private String yearBuild;

    @NotBlank(message = "Style of the Cultural Attraction is required")
    @Schema(description = "Style of the Cultural Attraction ")
    private String style;

    @Data
    @NoArgsConstructor
    public static class CulturalAttractionDtoResponse extends AttractionDtoResponse {

        private String yearBuild;
        private String style;
    }
}
