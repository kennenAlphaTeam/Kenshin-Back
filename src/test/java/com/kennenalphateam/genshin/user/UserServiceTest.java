package com.kennenalphateam.genshin.user;

import com.kennenalphateam.genshin.auth.jwt.JwtUser;
import com.kennenalphateam.genshin.mihoyo.GenshinInfoService;
import com.kennenalphateam.genshin.mihoyo.dto.GenshinIdCard;
import com.kennenalphateam.genshin.user.dto.UserCookieDto;
import com.kennenalphateam.genshin.user.entity.User;
import com.kennenalphateam.genshin.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @Mock
    private GenshinInfoService genshinInfoService;
    @InjectMocks
    private UserService userService;

    @Test
    void updateUserInfoTest() {
        User user = User.builder()
                .id(1L)
                .build();
        JwtUser jwtUser = new JwtUser(user);
        UserCookieDto cookieDto = new UserCookieDto();
        cookieDto.setCookie("ltuid=test;ltoken=test");

        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        given(userRepository.getById(anyLong())).willReturn(user);
        given(genshinInfoService.getGenshinUidFromCookie(anyString())).willReturn(new GenshinIdCard(jwtUser));

        userService.updateUserInfo(jwtUser, cookieDto);

        verify(userRepository).save(any());
        verify(genshinInfoService).getGenshinUidFromCookie(anyString());
    }
}