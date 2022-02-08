package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.SessionUser;
import com.kennenalphateam.genshin.user.dto.UserCookieDto;
import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    User sessionUserToUser(SessionUser sessionUser) {
        return userRepository.findById(sessionUser.getUserId()).orElseThrow();
    }

    SessionUser updateUserCookie(SessionUser sessionUser, UserCookieDto dto) {
        String cookie = dto.getCookie();
        User user = sessionUserToUser(sessionUser);

        user.updateCookie(cookie);
        userRepository.save(user);
        return new SessionUser(user);
    }
}
