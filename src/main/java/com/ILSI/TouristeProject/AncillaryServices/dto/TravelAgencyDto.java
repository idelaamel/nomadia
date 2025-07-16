package com.ILSI.TouristeProject.AncillaryServices.dto;

import com.ILSI.TouristeProject.AncillaryServices.AncillaryServiceDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TravelAgencyDto extends AncillaryServiceDto {

    @Schema( type = "string", example = " ")
    private String webSiteUrl;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TravelAgencyDtoResponse extends AncillaryServiceDtoResponse {

        private String webSiteUrl;
    }
}
