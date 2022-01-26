package com.kennenalphateam.genshin.auth;

import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.repository.UserRepository;
import com.kennenalphateam.genshin.user.util.OAuthType;
import lombok.RequiredArgsConstructor;
import org.hibernate.exception.SQLGrammarException;
import org.springframework.data.domain.Example;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User user = super.loadUser(userRequest);
        saveSessionUser(userRequest.getClientRegistration().getRegistrationId(), user);
        return user;
    }

    private void saveSessionUser(String registrationId, OAuth2User oAuth2User) {
        User user = saveOrLoadUser(registrationId, oAuth2User.getName());
        SessionUser sessionUser = new SessionUser(user);
        httpSession.setAttribute("user", sessionUser);
    }

    private User saveOrLoadUser(String registrationId, String oauthId) {
        OAuthType type = OAuthType.valueOf(registrationId.toUpperCase());

        User user = userRepository.findByOauthTypeAndOauthId(type, oauthId)
                .orElse(dataToUser(type, oauthId));
        return userRepository.save(user);
    }

    private User dataToUser(OAuthType type, String oauthId) {
        return  User.builder().oauthType(type).oauthId(oauthId).build();
    }
}
