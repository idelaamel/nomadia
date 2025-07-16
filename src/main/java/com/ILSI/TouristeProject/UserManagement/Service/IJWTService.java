package com.ILSI.TouristeProject.UserManagement.Service;

import org.springframework.security.core.userdetails.UserDetails;

public interface IJWTService {
    String extractUserName(String token);

    String generateToken(UserDetails userDetails);

    boolean isTokenValid(String token, UserDetails userDetails);
}
