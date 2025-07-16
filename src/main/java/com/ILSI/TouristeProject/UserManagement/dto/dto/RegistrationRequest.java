package com.ILSI.TouristeProject.UserManagement.dto.dto;


import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record RegistrationRequest(

        @Schema( type = "string", example = " ")
        @NotBlank(message = "Name is required")
        String user_name,

        @Schema( type = "string", example = " ")
        @NotBlank(message = "email is required")
        String email,

        @Schema( type = "string", example = " ")
        @NotBlank(message = "Password is required")
        String password
) {
}
