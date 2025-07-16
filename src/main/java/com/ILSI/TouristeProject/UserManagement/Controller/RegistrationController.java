package com.ILSI.TouristeProject.UserManagement.Controller;

import com.ILSI.TouristeProject.UserManagement.Exception.UserAlreadyExistException;
import com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl.AuthenticationService;
import com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl.CustomOAuth2UserService;
import com.ILSI.TouristeProject.UserManagement.dto.dto.JwtAuthenticationResponse;
import com.ILSI.TouristeProject.UserManagement.dto.dto.SignInRequest;
import com.ILSI.TouristeProject.UserManagement.event.RegistrationCompleteEvent;
import com.ILSI.TouristeProject.UserManagement.dto.dto.RegistrationRequest;
import com.ILSI.TouristeProject.UserManagement.model.VerificationToken;
import com.ILSI.TouristeProject.UserManagement.Repository.VerificationTokenRepository;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
@Tag(name="Create Account", description = "Create Your Account, Verify your Email and LogIn to your Account Just Her")
public class RegistrationController {

    private final UserService userService;
    private final ApplicationEventPublisher publisher;
    private final VerificationTokenRepository tokenRepository;
    private final AuthenticationService authenticationService;
    private final CustomOAuth2UserService auth2UserService;

    @PostMapping("/SignUp")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegistrationRequest registrationRequest, final HttpServletRequest request){
        try {
            AppUser user = userService.registerUser(registrationRequest);
            publisher.publishEvent(new RegistrationCompleteEvent(user, applicationUrl(request)));
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body("Success! please, check your email to complete your registration");
        } catch (UserAlreadyExistException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                    .body("User already exists: " + e.getMessage());
        }
    }

    @GetMapping("/verifyEmail")
    public ResponseEntity<String> verifyEmail(@RequestParam("verificationCode") String token) {
        VerificationToken theToken = tokenRepository.findByToken(token);

        if (theToken == null || theToken.getUser() == null) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid verification token");
        }
        if (theToken.getUser().isEnabled()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("This account is already verified, please log in");
        }
        if ( userService.validateToken(token)) {
            return ResponseEntity.ok("Email verified successfully. Now you can log in to your account");
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Invalid verification token");
    }

    private String applicationUrl(HttpServletRequest request) {
        return "http://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
    }


    @PostMapping("/SignIn")
    public ResponseEntity<JwtAuthenticationResponse> SignIn(@Valid @RequestBody SignInRequest signInRequest){
        return ResponseEntity.ok(authenticationService.signIn(signInRequest));
    }



}
