package com.ILSI.TouristeProject.AncillaryServices;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AncillaryServiceDto {

    @NotBlank(message = "Name is required")
    @Schema( type = "string", example = " ")
    private String name;

    @NotBlank(message = "Description is required")
    @Schema( type = "string", example = " ")
    private String description;

    @NotBlank(message = "Ancillary Service Phone Number is required")
    @Schema( type = "string", example = " ")
    private String phoneNumber;

    @NotBlank(message = "Ancillary Service email is required")
    @Schema( type = "string", example = " ")
    private String email;

    @NotBlank(message = "Operation Hours of the Ancillary Service is required")
    @Schema( type = "string", example = " ")
    private String operationHours;

    @NotNull(message = "Latitude of the Ancillary Service is required")
    @Schema( type = "string", example = "00.00")
    private double latitude;

    @NotNull(message = "Longitude of the Ancillary Service is required")
    @Schema( type = "string", example = "00.00")
    private double longitude;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AncillaryServiceDtoResponse {

        private Long id_AncillaryService;
        private String name;
        private String description;
        private String phoneNumber;
        private String email;
        private String operationHours;
        private double latitude;
        private double longitude;
    }
}
