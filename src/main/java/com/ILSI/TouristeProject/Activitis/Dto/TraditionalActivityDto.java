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
public class TraditionalActivityDto extends ActivityDto {

    @NotBlank(message = "Craft Type is required")
    @Schema(description = "craft type of the Traditional Activity")
    private String craftType;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class TraditionalActivityDtoResponse extends ActivityDtoResponse {

        private String craftType;
    }
}
