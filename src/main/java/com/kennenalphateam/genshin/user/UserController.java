package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.jwt.JwtService;
import com.kennenalphateam.genshin.auth.jwt.JwtUser;
import com.kennenalphateam.genshin.mihoyo.dto.GenshinIdCard;
import com.kennenalphateam.genshin.user.dto.UserCookieDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@RequestMapping("user")
@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    @PutMapping("me/cookie")
    ResponseEntity<GenshinIdCard> updateUserCookie(@AuthenticationPrincipal JwtUser user, @RequestBody UserCookieDto dto, HttpServletResponse response) {
        JwtUser updatedUser = userService.updateUserInfo(user, dto);

        Cookie updatedCookie = jwtService.generateTokenCookie(updatedUser);
        response.addCookie(updatedCookie);
        return ResponseEntity.ok()
                .body(new GenshinIdCard(updatedUser));
    }

    @GetMapping("me/genshinIdCard")
    public GenshinIdCard getGenshinIdCard(@AuthenticationPrincipal JwtUser user) {
        user.checkRegisteredGenshinUser();
        return new GenshinIdCard(user);
    }
}
