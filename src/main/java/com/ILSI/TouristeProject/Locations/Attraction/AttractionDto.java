package com.ILSI.TouristeProject.Locations.Attraction;

import com.ILSI.TouristeProject.Locations.Dto.LocationDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class AttractionDto extends LocationDto {

    @PositiveOrZero(message = "Entry Fre must be zero or positive")
    @Schema(description = "Entry fee of the location Ex: 20.20")
    private double entryFre;

    private boolean guideToursAvailable;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AttractionDtoResponse extends LocationDtoResponse {

        private double entryFre;
        private boolean guideToursAvailable;
    }
}
