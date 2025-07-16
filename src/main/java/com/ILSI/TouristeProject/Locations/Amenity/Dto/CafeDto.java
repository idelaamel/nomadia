package com.ILSI.TouristeProject.Locations.Amenity.Dto;

import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CafeDto extends AmenityDto {

    @NotBlank(message = "menu of the cafe is required")
    @Schema(description = "menu of the cafe Ex: cafe, lai, ...")
    private String menu;

    private boolean wifiAvailable;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class CafeDtoResponse extends AmenityDtoResponse {
        private String menu;
        private boolean wifiAvailable;
    }
}
