package com.ILSI.TouristeProject.Locations.Attraction.Dto;

import com.ILSI.TouristeProject.Locations.Attraction.AttractionDto;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class NaturalAttractionDto extends AttractionDto {

    private boolean protectedArea;

    @Data
    @NoArgsConstructor
    public static class NaturalAttractionDtoResponse extends AttractionDtoResponse {

        private boolean protectedArea;
    }
}
