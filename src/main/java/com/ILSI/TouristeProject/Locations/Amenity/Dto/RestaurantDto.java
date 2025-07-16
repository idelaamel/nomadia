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
public class RestaurantDto extends AmenityDto {

    @NotBlank(message = "menu of the Hotel is required")
    @Schema(description = "menu of the cafe Ex: Tajin, madfouna, ...")
    private String menu;

    @NotBlank(message = "type Cuisine of the Restaurant is required")
    @Schema(description = "type Cuisine of the Restaurant")
    private String typeCuisine;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class RestaurantDtoResponse extends AmenityDtoResponse {

        private String menu;
        private String typeCuisine;

    }

}
