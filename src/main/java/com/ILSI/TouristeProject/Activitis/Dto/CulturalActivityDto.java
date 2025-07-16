package com.ILSI.TouristeProject.Activitis.Dto;

import com.ILSI.TouristeProject.Activitis.ActivityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CulturalActivityDto extends ActivityDto {

    @NotBlank(message = "tradition Associated is required")
    @Schema(description = "tradition Associated to the Cultural Activity")
    private String traditionAssociated;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CulturalActivityDtoResponse extends ActivityDtoResponse {

        private String traditionAssociated;
    }
}
