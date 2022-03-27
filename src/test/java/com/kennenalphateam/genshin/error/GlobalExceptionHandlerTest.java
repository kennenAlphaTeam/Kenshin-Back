package com.kennenalphateam.genshin.error;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kennenalphateam.genshin.GenshinApplication;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Controller;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.bind.annotation.GetMapping;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = {GenshinApplication.class,
                GlobalExceptionHandlerTest.ThrowExceptionController.class})
@AutoConfigureMockMvc(addFilters = false)
class GlobalExceptionHandlerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    ErrorResponse toConvertToErrorResponse(MvcResult result) {
        try {
            String content = result.getResponse().getContentAsString();
            return mapper.readValue(content, ErrorResponse.class);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void 존재하지않는_ErrorCode_에러발생시_ErrorResponse가_반환된다() throws Exception {
        mockMvc.perform(get("/exception"))
                .andExpect(status().is5xxServerError())
                .andExpect(this::toConvertToErrorResponse);
    }

    @Test
    void 존재하는_ErrorCode_에러발생시_그에_맞는_ErrorResponse가_반환된다() throws Exception {
        MvcResult result = mockMvc.perform(get("/errorException"))
                .andExpect(status().is4xxClientError())
                .andExpect(r -> assertTrue(r.getResolvedException() instanceof ErrorException))
                .andReturn();
        ErrorResponse response = toConvertToErrorResponse(result);

        assertEquals(response.getMessage(), ErrorCode.USER_UNAUTHORIZED_ERROR.getDetail());
    }

    @Controller
    static class ThrowExceptionController {
        @GetMapping("exception")
        void throwException() {
            throw new RuntimeException();
        }

        @GetMapping("errorException")
        void throwUserUnauthorizedError() {
            throw new ErrorException(ErrorCode.USER_UNAUTHORIZED_ERROR);
        }
    }
}