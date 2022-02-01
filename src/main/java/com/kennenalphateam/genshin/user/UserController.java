package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.SessionUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping("user")
@RestController
public class UserController {

    @GetMapping("profile/me")
    String getMyProfile(@LoginUser SessionUser user) {
        return "profile";
    }
}
