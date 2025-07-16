package com.ILSI.TouristeProject.UserManagement.dto.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;

public record SignInRequest(

        @Schema( type = "string", example = " ")
        @NotBlank(message = "Email required! Enter your email")
        String email,

        @Schema( type = "string", example = " ")
        @NotBlank(message = "Password required! Enter your Password")
        String password
) {
}
