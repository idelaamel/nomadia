package com.ILSI.TouristeProject.Activitis.Dto;

import com.ILSI.TouristeProject.Activitis.ActivityDto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AdventureActivityDto extends ActivityDto {

    @NotBlank(message = "terrain Type is required")
    @Schema(description = "terrain Type of the Adventure Activity")
    private String terrainType;

    @NotNull(message = "age Restriction is required")
    @Positive(message = "Age must be greater than 5")
    @Schema(description = "age limit required to this Adventure Activity")
    private int ageRestriction;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class AdventureActivityDtoResponse extends ActivityDtoResponse {
        private String terrainType;
        private int ageRestriction;

    }
}
