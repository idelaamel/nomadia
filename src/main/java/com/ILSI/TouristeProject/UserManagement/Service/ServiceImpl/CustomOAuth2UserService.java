package com.ILSI.TouristeProject.UserManagement.Service.ServiceImpl;

import com.ILSI.TouristeProject.UserManagement.Repository.UserRepository;
import com.ILSI.TouristeProject.UserManagement.model.AppUser;
import com.ILSI.TouristeProject.UserManagement.model.Role;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest request) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(request);
        String email = oAuth2User.getAttribute("email");
        String name = oAuth2User.getAttribute("name");

        if (email == null) {
            throw new OAuth2AuthenticationException("Email not found from OAuth2 provider");
        }

        Optional<AppUser> optionalUser = userRepository.findByEmail(email);
        if (optionalUser.isEmpty()) {
            AppUser user = new AppUser();
            user.setNameUser(name);
            user.setEmail(email);
            user.setPassword("google");
            user.setEnabled(true);
            user.setRole(Role.USER);
            userRepository.save(user);
        }
        return oAuth2User;
    }
}
