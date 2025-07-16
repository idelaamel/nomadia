package com.ILSI.TouristeProject.AncillaryServices.dto;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SanitaryDto extends AncillaryServiceDto {

    private boolean emergencySupport;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SanitaryDtoResponse extends AncillaryServiceDtoResponse {

        private boolean emergencySupport;
    }
}
