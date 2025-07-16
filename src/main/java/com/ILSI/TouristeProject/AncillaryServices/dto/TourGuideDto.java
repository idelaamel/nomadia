package com.ILSI.TouristeProject.AncillaryServices.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TourGuideDto extends AdministrativeDto{

    @NotNull(message = "group Size Limit is required")
    @Positive(message = "group Size Limit must be greater than 1")
    @Schema( type = "string", example = " ")
    private int groupSizeLimit;

    @NotBlank(message = "language Supported is required")
    @Schema( type = "string", example = "francais, Anglaise,...")
    private String languageSupported;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TourGuideDtoResponse extends AdministrativeDtoResponse{


        private int groupSizeLimit;
        private String languageSupported;
    }
}
