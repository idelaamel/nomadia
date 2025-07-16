package com.ILSI.TouristeProject.UserManagement.Service;

import com.ILSI.TouristeProject.UserManagement.dto.dto.JwtAuthenticationResponse;
import com.ILSI.TouristeProject.UserManagement.dto.dto.SignInRequest;

public interface IAuthenticationService {

    //AppUser signUp(SignUpRequest signUpRequest);
    JwtAuthenticationResponse signIn(SignInRequest signInRequest);
}
