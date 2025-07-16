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
public class SportiveActivityDto extends ActivityDto {

    @NotBlank(message = "Type sport is required")
    @Schema(description = "type sport of the Sportive Activity")
    private String typeSport;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SportiveActivityDtoResponse extends ActivityDtoResponse {

        private String typeSport;
    }
}
