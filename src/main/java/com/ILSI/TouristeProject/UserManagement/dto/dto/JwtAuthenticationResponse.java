package com.ILSI.TouristeProject.UserManagement.dto.dto;


import lombok.Data;

@Data
public class JwtAuthenticationResponse {
    private Long id_User;
    private String token;
    private String role;
    private String nameUser;

}
