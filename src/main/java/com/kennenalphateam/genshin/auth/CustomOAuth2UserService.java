package com.kennenalphateam.genshin.auth;

import com.kennenalphateam.genshin.error.ErrorCode;
import com.kennenalphateam.genshin.error.ErrorException;
import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.repository.UserRepository;
import com.kennenalphateam.genshin.user.util.OAuthType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final UserRepository userRepository;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User baseUser = super.loadUser(userRequest);
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();
        User realUser = saveOrLoadUser(userRequest.getClientRegistration().getRegistrationId(), baseUser.getName());

        Map<String, Object> attributes = new HashMap<>(baseUser.getAttributes());
        attributes.put("user", realUser);
        return new DefaultOAuth2User(AuthUtils.getDefaultAuthorities(), attributes, userNameAttributeName);
    }

    public User saveOrLoadUser(String registrationId, String oauthId) {
        OAuthType type = OAuthType.valueOf(registrationId.toUpperCase());

        User user = userRepository.findByOauthTypeAndOauthId(type, oauthId)
                .orElse(dataToUser(type, oauthId));
        return userRepository.save(user);
    }

    public User dataToUser(OAuthType type, String oauthId) {
        return User.builder().oauthType(type).oauthId(oauthId).build();
    }
}
