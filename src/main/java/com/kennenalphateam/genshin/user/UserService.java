package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.jwt.JwtUser;
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

    public JwtUser updateUserInfo(JwtUser jwtUser, UserCookieDto dto) {
        String cookie = MihoyoUtils.minifyMihoyoCookie(dto.getCookie());
        MihoyoUtils.updateRequestContextMihoyoCookie(cookie);
        GenshinIdCard genshinIdCard = genshinInfoService.getGenshinUidFromCookie(cookie);

        User user = userRepository.getById(jwtUser.getUserId());

        user.updateCookie(cookie);
        user.updateGenshinId(genshinIdCard);
        userRepository.save(user);
        return new JwtUser(user);
    }
}
