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
public class AdministrativeDto extends AncillaryServiceDto {

    private boolean requiresReservation;

    @Schema( type = "string", example = " ")
    private String documentRequired;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AdministrativeDtoResponse extends AncillaryServiceDtoResponse {

        private boolean requiresReservation;
        private String documentRequired;
    }
}
