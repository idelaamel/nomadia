package com.ILSI.TouristeProject.UserManagement.Service;

import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import com.ILSI.TouristeProject.UserManagement.dto.dto.RegistrationRequest;


import java.util.List;
import java.util.Optional;

public interface IUserService {

    List<AppUser> getUsers();

    AppUser registerUser(RegistrationRequest request);

    Optional<AppUser> findByEmail(String email);

    void saveUserVerificationToken(AppUser theUser, String token);

    boolean validateToken(String theToken);

}
