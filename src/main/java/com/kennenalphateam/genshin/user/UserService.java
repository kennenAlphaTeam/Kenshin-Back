package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.SessionUser;
import com.kennenalphateam.genshin.mihoyo.GenshinInfoService;
import com.kennenalphateam.genshin.mihoyo.MihoyoUtils;
import com.kennenalphateam.genshin.mihoyo.dto.GenshinIdCard;
import com.kennenalphateam.genshin.user.dto.UserCookieDto;
import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;
    private final GenshinInfoService genshinInfoService;

    public User sessionUserToUser(SessionUser sessionUser) {
        return userRepository.findById(sessionUser.getUserId()).orElseThrow();
    }

    public SessionUser updateUserInfo(SessionUser sessionUser, UserCookieDto dto) {
        String cookie = MihoyoUtils.minifyMihoyoCookie(dto.getCookie());
        User user = sessionUserToUser(sessionUser);
        GenshinIdCard genshinIdCard = genshinInfoService.getGenshinUidFromCookie(cookie);

        user.updateCookie(cookie);
        user.updateGenshinId(genshinIdCard);
        userRepository.save(user);
        return new SessionUser(user);
    }

    public GenshinIdCard getGenshinIdCardBySessionUser(SessionUser sessionUser) {
        return new GenshinIdCard(sessionUser);
    }
}
