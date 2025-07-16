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
public class GuestHouseDto extends AmenityDto {

    @NotNull(message = "Number Rooms is required")
    @Schema(description = "Number Roms of the camping")
    private int numberRooms;

    private boolean BreakfastIncluded;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class GuestHouseDtoResponse extends AmenityDtoResponse {

        private int numberRooms;
        private boolean BreakfastIncluded;
    }
}
