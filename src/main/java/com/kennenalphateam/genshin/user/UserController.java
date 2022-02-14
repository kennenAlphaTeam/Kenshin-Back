package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.SessionUser;
import com.kennenalphateam.genshin.user.dto.UserCookieDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Slf4j
@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final HttpSession httpSession;

    @PutMapping("me/cookie")
    void updateUserCookie(@LoginUser SessionUser user, @RequestBody UserCookieDto dto) {
        SessionUser updatedUser = userService.updateUserInfo(user, dto);
        httpSession.setAttribute("user", updatedUser);
    }
}
