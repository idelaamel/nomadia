package com.ILSI.TouristeProject.Locations.Amenity.Dto;

import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HotelDto extends AmenityDto {

    @NotNull(message = "number Stars is required")
    @Schema(description = "number Stars of the Hotel")
    private int numberStars;

    @NotNull(message = "Number Rooms is required")
    @Schema(description = "Number Roms of the Hotel")
    private int numberOfRooms;

    private boolean hasSwimmingPool;



    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class HotelDtoResponse extends AmenityDtoResponse {

        private int numberStars;
        private int numberOfRooms;
        private boolean hasSwimmingPool;
    }
}
