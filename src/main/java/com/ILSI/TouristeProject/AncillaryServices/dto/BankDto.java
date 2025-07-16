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
public class BankDto extends AncillaryServiceDto {

    @NotBlank(message= "Bank Name is required")
    @Schema( type = "string", example = " ")
    private String bankName;

    private boolean currencyExchange;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class BankDtoResponse extends AncillaryServiceDtoResponse {

        private String bankName;
        private boolean currencyExchange;
    }
}
