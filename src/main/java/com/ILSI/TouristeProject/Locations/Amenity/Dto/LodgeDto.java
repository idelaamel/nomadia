package com.ILSI.TouristeProject.Locations.Amenity.Dto;

import com.ILSI.TouristeProject.Locations.Amenity.AmenityDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LodgeDto extends AmenityDto {

    private boolean viewPanoramic;

    private boolean closeNature;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class LodgeDtoResponse extends AmenityDtoResponse {

        private boolean viewPanoramic;
        private boolean closeNature;
    }

}
