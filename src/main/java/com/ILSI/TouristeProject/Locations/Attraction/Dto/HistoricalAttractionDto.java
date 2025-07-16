package com.ILSI.TouristeProject.Locations.Attraction.Dto;

import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import lombok.Data;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class HistoricalAttractionDto extends AttractionDto {

    @NotBlank(message = "Style of the Historical Attraction is required")
    @Schema(description = "Style of the Historical Attraction")
    private  String style;

    @Data
    @NoArgsConstructor
    public static class HistoricalAttractionDtoResponse extends AttractionDtoResponse {

        private  String style;

    }
}
