package com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl;

import com.ILSI.TouristeProject.UserManagement.Repository.UserRepository;
import com.ILSI.TouristeProject.UserManagement.Service.IAuthenticationService;
import com.ILSI.TouristeProject.UserManagement.dto.dto.JwtAuthenticationResponse;
import com.ILSI.TouristeProject.UserManagement.dto.dto.SignInRequest;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService implements IAuthenticationService {

    private final UserRepository appUserRepository;

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;


    @Override
    public JwtAuthenticationResponse signIn(SignInRequest signInRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken( signInRequest.email(), signInRequest.password()));
        var user = appUserRepository.findByEmail(signInRequest.email()).orElseThrow(()-> new IllegalArgumentException("Invalid email or password"));
        var jwt = jwtService.generateToken(user);
        JwtAuthenticationResponse jwtAuthenticationResponse = new JwtAuthenticationResponse();
        jwtAuthenticationResponse.setId_User(user.getId());
        jwtAuthenticationResponse.setNameUser(user.getNameUser());
        jwtAuthenticationResponse.setToken(jwt);
        jwtAuthenticationResponse.setRole(user.getRole().toString());
        return jwtAuthenticationResponse;
    }
}
