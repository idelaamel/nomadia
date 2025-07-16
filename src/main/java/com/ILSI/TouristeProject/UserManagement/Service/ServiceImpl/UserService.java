package com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl;

import com.ILSI.TouristeProject.UserManagement.Exception.UserAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Service.IUserService;
import com.ILSI.TouristeProject.UserManagement.Repository.UserRepository;
import com.ILSI.TouristeProject.UserManagement.Repository.VerificationTokenRepository;
import com.ILSI.TouristeProject.UserManagement.model.Role;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import com.ILSI.TouristeProject.UserManagement.dto.dto.RegistrationRequest;
import com.ILSI.TouristeProject.UserManagement.model.VerificationToken;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final VerificationTokenRepository tokenRepository;

    @Override
    public List<AppUser> getUsers() {
        return userRepository.findAll();
    }


    @Override
    public AppUser registerUser(RegistrationRequest request) {
        Optional<AppUser> user = this.findByEmail(request.email());
        if(user.isPresent()){
            throw new UserAlreadyExistException(" User with email "+request.email()+" already exists");
        }
        var newUser = new AppUser();
        newUser.setNameUser(request.user_name());
        newUser.setEmail(request.email());
        newUser.setPassword(passwordEncoder.encode(request.password()));
        newUser.setRole(Role.USER);

        return userRepository.save(newUser);
    }


    @Override
    public Optional<AppUser> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public void saveUserVerificationToken(AppUser theUser, String token) {
        var verificationToken = new VerificationToken(token, theUser);
        tokenRepository.save(verificationToken);
    }

    @Override
    public boolean validateToken(String theToken) {
        VerificationToken token = tokenRepository.findByToken(theToken);
        if(token ==null){
           // "Invalid verification token"
            return false;
        }
        AppUser user = token.getUser();
        Calendar calendar = Calendar.getInstance();
        if((token.getExpirationTime().getTime() - calendar.getTime().getTime())<= 0){
            tokenRepository.delete(token);
            //"Token already expired"
            return false;
        }
        user.setEnabled(true);
        userRepository.save(user);
        //"verification valid"
        return true;
    }



}
