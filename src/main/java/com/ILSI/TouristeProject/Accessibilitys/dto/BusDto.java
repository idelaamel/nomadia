package com.ILSI.TouristeProject.Accessibilitys.dto;

import com.ILSI.TouristeProject.Accessibilitys.AccessibilityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class BusDto extends AccessibilityDto {

    @NotBlank(message = "Name of company is required")
    @Schema(description = "Name of Bus's Company")
    private String company;

    @NotBlank(message = "routeName is required")
    @Schema(description = "Name of Bus's Route")
    private String routeName;

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class BusDtoResponse extends AccessibilityDtoResponse {

        private String company;
        private String routeName;
    }
}
